package br.com.digitalhouse.projetointegradorpi.domain.service;

import br.com.digitalhouse.projetointegradorpi.domain.entity.Carro;
import br.com.digitalhouse.projetointegradorpi.domain.entity.Categoria;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Set;
import java.util.UUID;

public interface CarroService {
    Carro criarCarro(Carro carro, UUID categoriaId, UUID cidadeId, Set<UUID> caracteristicaCarroId);
    Page<Carro> buscarCarros(Pageable page, String termo, String cidade);
    Carro buscarCarroPorId (UUID id);
}
