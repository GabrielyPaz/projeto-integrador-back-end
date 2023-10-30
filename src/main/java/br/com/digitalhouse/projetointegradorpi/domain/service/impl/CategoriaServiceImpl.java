package br.com.digitalhouse.projetointegradorpi.domain.service.impl;

import br.com.digitalhouse.projetointegradorpi.domain.entity.Categoria;
import br.com.digitalhouse.projetointegradorpi.domain.exceptions.CategoryNotFoundException;
import br.com.digitalhouse.projetointegradorpi.domain.repository.CategoriaRepository;
import br.com.digitalhouse.projetointegradorpi.domain.service.CategoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
        Categoria categoriaAtual = this.categoriaRepository.findById(id).orElseThrow(() ->new CategoryNotFoundException(id));

        categoriaAtual.setDescricao(categoria.getDescricao());
        categoriaAtual.setQualificacao(categoria.getQualificacao());
        categoriaAtual.setUrlImagem(categoria.getUrlImagem());

        return categoriaRepository.save(categoria);
    }

    @Override
    public List<Categoria> buscarCategorias(String nome) {
        return this.categoriaRepository.findAll();
    }

    @Override
    public void deletarCategoria(UUID id) {
        Categoria categoria = categoriaRepository
                .findById(id)
                .orElseThrow(()-> new CategoryNotFoundException(id));
        categoriaRepository.delete(categoria);
    }
}
