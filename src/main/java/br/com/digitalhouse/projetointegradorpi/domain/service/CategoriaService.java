package br.com.digitalhouse.projetointegradorpi.domain.service;

import br.com.digitalhouse.projetointegradorpi.domain.entity.Categoria;

import java.util.List;
import java.util.UUID;

public interface CategoriaService {
     Categoria criarCategoria(Categoria categoria);

     Categoria atualizarCategoria(UUID id, Categoria categoria);

     List<Categoria> buscarCategorias(String nome);
     void deletarCategoria(UUID id);
}
