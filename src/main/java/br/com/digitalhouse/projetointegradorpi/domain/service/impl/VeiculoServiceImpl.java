package br.com.digitalhouse.projetointegradorpi.domain.service.impl;

import br.com.digitalhouse.projetointegradorpi.domain.entity.*;
import br.com.digitalhouse.projetointegradorpi.domain.exceptions.CarNotFoundException;
import br.com.digitalhouse.projetointegradorpi.domain.exceptions.CategoryNotFoundException;
import br.com.digitalhouse.projetointegradorpi.domain.exceptions.CidadeNotFoundException;
import br.com.digitalhouse.projetointegradorpi.domain.filter.FiltroVeiculo;
import br.com.digitalhouse.projetointegradorpi.domain.repository.CaracteristicaRespository;
import br.com.digitalhouse.projetointegradorpi.domain.repository.VeiculoRepository;
import br.com.digitalhouse.projetointegradorpi.domain.repository.CategoriaRepository;
import br.com.digitalhouse.projetointegradorpi.domain.repository.CidadeRepository;
import br.com.digitalhouse.projetointegradorpi.domain.service.VeiculoService;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import jakarta.persistence.criteria.Subquery;
import lombok.AllArgsConstructor;
import org.hibernate.persister.collection.mutation.RowMutationOperations;
import org.hibernate.sql.Restriction;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;

@Service

@AllArgsConstructor
public class VeiculoServiceImpl implements VeiculoService {

    private final VeiculoRepository veiculoRepository;
    private final CategoriaRepository categoriaRepository;
    private final CidadeRepository cidadeRepository;
    private final CaracteristicaRespository caracteristicaRespository;

    @Override
    public Veiculo criarCarro(Veiculo veiculo, UUID categoriaId, UUID cidadeId, Set<UUID> caracteristicaCarroId) {
        Categoria categoria = categoriaRepository.findById(categoriaId)
                .orElseThrow(() -> new CategoryNotFoundException(categoriaId));
        veiculo.setCategoria(categoria);

        Cidade cidade = cidadeRepository.findById(cidadeId)
                .orElseThrow(() -> new CidadeNotFoundException(cidadeId));
        veiculo.setCidade(cidade);

        List<Caracteristica> caracteristicaList = caracteristicaRespository
                .findAllById(caracteristicaCarroId);
        Set<Caracteristica> caracteristicaSet = new HashSet<>(caracteristicaList);

        veiculo.setCaracteristicasCarro(caracteristicaSet);
        return this.veiculoRepository.save(veiculo);
    }

    @Override
    public Page<Veiculo> buscarCarros(Pageable page, FiltroVeiculo filtroVeiculo) {
        String termo = filtroVeiculo.getTermo();
        String cidade = filtroVeiculo.getCidade();
        LocalDateTime dataInicial = filtroVeiculo.getDataInicial();
        LocalDateTime dataFinal = filtroVeiculo.getDataFinal();
        return this.veiculoRepository.findAll((root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();
            if (termo != null && !termo.isBlank()) {
                var searchTerm = "%" + termo + "%";
                Predicate nome = criteriaBuilder.like(root.get("modelo"), searchTerm);
                predicates.add(nome);
            }
            if (cidade != null && !cidade.isBlank()) {
                Predicate nome = criteriaBuilder.like(root.get("cidade").get("nome"), cidade);
                predicates.add(nome);
            }
            if (dataInicial != null && dataFinal != null) {
                Subquery<Reserva> subquery = query.subquery(Reserva.class);
                Root<Reserva> reservaRoot = subquery.from(Reserva.class);

                subquery.select(reservaRoot)
                        .where(criteriaBuilder.equal(reservaRoot.get("veiculo"), root),
                                criteriaBuilder.lessThanOrEqualTo(reservaRoot.get("dataInicial"), dataFinal),
                                criteriaBuilder.greaterThanOrEqualTo(reservaRoot.get("dataFinal"), dataInicial));

                predicates.add(criteriaBuilder.not(criteriaBuilder.exists(subquery)));
            }
            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        }, page);
    }

    @Override
    public Veiculo buscarCarroPorId(UUID id) {
        return this.veiculoRepository
                .findById(id)
                .orElseThrow(() -> new CarNotFoundException(id));
    }
}
