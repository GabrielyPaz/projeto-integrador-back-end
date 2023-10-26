package br.com.digitalhouse.projetointegradorpi.domain.service.impl;

import br.com.digitalhouse.projetointegradorpi.domain.entity.Categoria;
import br.com.digitalhouse.projetointegradorpi.domain.service.CategoriaService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class CategoriaServiceImpl implements CategoriaService {
    @Override
    public Categoria criarCategoria(Categoria categoria) {
        return null;
    }

    @Override
    public Categoria atualizarCategoria(UUID id, Categoria categoria) {
        return null;
    }

    @Override
    public List<Categoria> buscarCategorias() {
        return null;
    }

    @Override
    public void deletarCategoria(UUID id) {

    }
}
