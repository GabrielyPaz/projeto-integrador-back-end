package br.com.digitalhouse.projetointegradorpi.domain.service.impl;

import br.com.digitalhouse.projetointegradorpi.domain.entity.Cidade;
import br.com.digitalhouse.projetointegradorpi.domain.repository.CidadeRepository;
import br.com.digitalhouse.projetointegradorpi.domain.service.CidadeService;
import jakarta.persistence.criteria.Predicate;
import org.hibernate.engine.spi.SessionDelegatorBaseImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CidadeServiceImpl implements CidadeService {

    private final CidadeRepository cidadeRepository;

    @Autowired
    public CidadeServiceImpl(CidadeRepository cidadeRepository) {
        this.cidadeRepository = cidadeRepository;
    }

    @Override
    public Cidade criarCidade(Cidade cidade) {
        return cidadeRepository.save(cidade);
    }

    @Override
    public Page<Cidade> buscarCidades(Pageable page, String termo) {
        return this.cidadeRepository.findAll((root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();
            if (termo != null && !termo.isBlank()) {
                var searchTerm = "%" + termo + "%";
                Predicate nome = criteriaBuilder.like(root.get("nome"), searchTerm);
                predicates.add(nome);
            }
            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        }, page);
    }
}