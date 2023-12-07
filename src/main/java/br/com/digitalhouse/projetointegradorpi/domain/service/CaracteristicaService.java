package br.com.digitalhouse.projetointegradorpi.domain.service;

import br.com.digitalhouse.projetointegradorpi.domain.entity.Caracteristica;
import br.com.digitalhouse.projetointegradorpi.domain.entity.Categoria;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CaracteristicaService {
    Caracteristica criarCaracteristica(Caracteristica caracteristica);

    Page<Caracteristica> buscarCaracteristica(Pageable page, String termo);
}
