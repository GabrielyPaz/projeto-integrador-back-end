package br.com.digitalhouse.projetointegradorpi.domain.service;

import br.com.digitalhouse.projetointegradorpi.domain.entity.Reserva;

import java.util.UUID;


public interface ReservaService {
    Reserva criarNovaReserva(Reserva reserva, UUID carroId, UUID usuarioId);

    Reserva consultarReservaPorId(UUID id);

    void deletarReserva(UUID id);
}


