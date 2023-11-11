package br.com.digitalhouse.projetointegradorpi.domain.service;

import br.com.digitalhouse.projetointegradorpi.domain.entity.Carro;
import br.com.digitalhouse.projetointegradorpi.domain.entity.Categoria;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.UUID;

public interface CarroService {
    Carro criarCarro(Carro carro);
    Page<Carro> buscarCarros(Pageable page, String termo);
    Carro buscarCarroPorId (UUID id);
}
