package br.com.digitalhouse.projetointegradorpi.domain.service;

import br.com.digitalhouse.projetointegradorpi.domain.entity.Categoria;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.UUID;

public interface CategoriaService {
    Categoria criarCategoria(Categoria categoria);

    Categoria atualizarCategoria(UUID id, Categoria categoria);

    Page<Categoria> buscarCategorias(Pageable page, String termo);

    Categoria buscarCategoriaPorId(UUID id);

    void deletarCategoria(UUID id);
}
