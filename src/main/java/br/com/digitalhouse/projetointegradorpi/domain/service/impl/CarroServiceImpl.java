package br.com.digitalhouse.projetointegradorpi.domain.service.impl;

import br.com.digitalhouse.projetointegradorpi.domain.entity.Caracteristica;
import br.com.digitalhouse.projetointegradorpi.domain.entity.Carro;
import br.com.digitalhouse.projetointegradorpi.domain.entity.Categoria;
import br.com.digitalhouse.projetointegradorpi.domain.entity.Cidade;
import br.com.digitalhouse.projetointegradorpi.domain.exceptions.CarNotFoundException;
import br.com.digitalhouse.projetointegradorpi.domain.exceptions.CategoryNotFoundException;
import br.com.digitalhouse.projetointegradorpi.domain.exceptions.CidadeNotFoundException;
import br.com.digitalhouse.projetointegradorpi.domain.filter.FiltroCarro;
import br.com.digitalhouse.projetointegradorpi.domain.repository.CaracteristicaRespository;
import br.com.digitalhouse.projetointegradorpi.domain.repository.CarroRepository;
import br.com.digitalhouse.projetointegradorpi.domain.repository.CategoriaRepository;
import br.com.digitalhouse.projetointegradorpi.domain.repository.CidadeRepository;
import br.com.digitalhouse.projetointegradorpi.domain.service.CarroService;
import jakarta.persistence.criteria.Predicate;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.*;

@Service

@AllArgsConstructor
public class CarroServiceImpl implements CarroService {

    private final CarroRepository carroRepository;
    private final CategoriaRepository categoriaRepository;
    private final CidadeRepository cidadeRepository;
    private final CaracteristicaRespository caracteristicaRespository;

    @Override
    public Carro criarCarro(Carro carro, UUID categoriaId, UUID cidadeId, Set<UUID> caracteristicaCarroId) {
        Categoria categoria = categoriaRepository.findById(categoriaId)
                .orElseThrow(() -> new CategoryNotFoundException(categoriaId));
        carro.setCategoria(categoria);

        Cidade cidade = cidadeRepository.findById(cidadeId)
                .orElseThrow(() -> new CidadeNotFoundException(cidadeId));
        carro.setCidade(cidade);

        List<Caracteristica> caracteristicaList = caracteristicaRespository
                .findAllById(caracteristicaCarroId);
        Set<Caracteristica> caracteristicaSet = new HashSet<>(caracteristicaList);

        carro.setCaracteristicasCarro(caracteristicaSet);
        return this.carroRepository.save(carro);
    }

    @Override
    public Page<Carro> buscarCarros(Pageable page, FiltroCarro filtroCarro) {
        String termo = filtroCarro.getTermo();
        String cidade = filtroCarro.getCidade();
        return this.carroRepository.findAll((root, query, criteriaBuilder) -> {
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
            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        }, page);
    }

    @Override
    public Carro buscarCarroPorId(UUID id) {
        return this.carroRepository
                .findById(id)
                .orElseThrow(() -> new CarNotFoundException(id));
    }
}
