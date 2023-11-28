package br.com.digitalhouse.projetointegradorpi.domain.service;

import br.com.digitalhouse.projetointegradorpi.domain.entity.Veiculo;
import br.com.digitalhouse.projetointegradorpi.domain.filter.FiltroVeiculo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Set;
import java.util.UUID;

public interface VeiculoService {
    Veiculo criarCarro(Veiculo veiculo, UUID categoriaId, UUID cidadeId, Set<UUID> caracteristicaCarroId);

    Page<Veiculo> buscarCarros(Pageable page, FiltroVeiculo filtroVeiculo);

    Veiculo buscarCarroPorId(UUID id);
}
