package br.com.digitalhouse.projetointegradorpi.domain.service.impl;

import br.com.digitalhouse.projetointegradorpi.domain.entity.Categoria;
import br.com.digitalhouse.projetointegradorpi.domain.exceptions.CategoryNotFoundException;
import br.com.digitalhouse.projetointegradorpi.domain.repository.CategoriaRepository;
import br.com.digitalhouse.projetointegradorpi.domain.service.CategoriaService;
import jakarta.persistence.criteria.Predicate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class CategoriaServiceImpl implements CategoriaService {

    private final CategoriaRepository categoriaRepository;

    @Autowired
    public CategoriaServiceImpl(CategoriaRepository categoriaRepository) {
        this.categoriaRepository = categoriaRepository;
    }

    @Override
    public Categoria criarCategoria(Categoria categoria) {
        return categoriaRepository.save(categoria);

    }


    @Override
    public Categoria atualizarCategoria(UUID id, Categoria categoria) {
        Categoria categoriaAtual = this.categoriaRepository.findById(id).orElseThrow(() -> new CategoryNotFoundException(id));

        categoriaAtual.setDescricao(categoria.getDescricao());
        categoriaAtual.setQualificacao(categoria.getQualificacao());
        //categoriaAtual.setUrlImagem(categoria.getUrlImagem());

        return categoriaRepository.save(categoriaAtual);
    }

    @Override
    public Page<Categoria> buscarCategorias(Pageable page, String termo) {
        return this.categoriaRepository.findAll((root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();
            if (termo != null && !termo.isBlank()) {
                var searchTerm = "%" + termo + "%";
                Predicate nome = criteriaBuilder.like(root.get("nome"), searchTerm);
                predicates.add(nome);
            }
            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        }, page);
    }

    @Override
    public Categoria buscarCategoriaPorId(UUID id) {
        return this.categoriaRepository
                .findById(id)
                .orElseThrow(() -> new CategoryNotFoundException(id));
    }

    @Override
    public void deletarCategoria(UUID id) {
        Categoria categoria = categoriaRepository
                .findById(id)
                .orElseThrow(() -> new CategoryNotFoundException(id));
        categoriaRepository.delete(categoria);
    }
}
