package br.com.digitalhouse.projetointegradorpi.api.controller;

import br.com.digitalhouse.projetointegradorpi.api.ReservaApi;
import br.com.digitalhouse.projetointegradorpi.api.dto.request.ReservaRequest;
import br.com.digitalhouse.projetointegradorpi.api.dto.response.ReservaResponse;
import br.com.digitalhouse.projetointegradorpi.domain.entity.Reserva;
import br.com.digitalhouse.projetointegradorpi.domain.service.ReservaService;
import br.com.digitalhouse.projetointegradorpi.infrastructure.mapper.ReservaMapper;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController

public class ReservaController implements ReservaApi {

    private final ObjectMapper objectMapper;
    private final ReservaService reservaService;
    private final ReservaMapper reservaMapper;

    @Autowired
    public ReservaController(ObjectMapper objectMapper,
                             ReservaService reservaService,
                             ReservaMapper reservaMapper) {
        this.objectMapper = objectMapper;
        this.reservaService = reservaService;
        this.reservaMapper = reservaMapper;
    }

    @Override
    public ResponseEntity<ReservaResponse> criarNovaReserva(ReservaRequest request) {
        Reserva reserva = objectMapper.convertValue(request, Reserva.class);
        Reserva reservaCriada = reservaService.criarNovaReserva(reserva, request.getCarroId(), request.getUsuarioId());
        ReservaResponse response = reservaResponseByReserva(reservaCriada);
        return ResponseEntity.ok(response);
    }

    @Override
    public ResponseEntity<ReservaResponse> consultarReservaPorId(UUID id) {
        Reserva reserva = reservaService.consultarReservaPorId(id);
        ReservaResponse response = reservaResponseByReserva(reserva);
        return ResponseEntity.ok(response);
    }

    private ReservaResponse reservaResponseByReserva(Reserva reserva) {
        return reservaMapper.reservaParaReservaResponse(reserva);
    }
}
