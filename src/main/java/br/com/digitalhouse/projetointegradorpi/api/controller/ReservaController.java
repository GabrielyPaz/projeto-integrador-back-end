package br.com.digitalhouse.projetointegradorpi.api.controller;

import br.com.digitalhouse.projetointegradorpi.api.ReservaApi;
import br.com.digitalhouse.projetointegradorpi.api.dto.request.ReservaRequest;
import br.com.digitalhouse.projetointegradorpi.api.dto.response.ReservaResponse;
import br.com.digitalhouse.projetointegradorpi.domain.entity.Reserva;
import br.com.digitalhouse.projetointegradorpi.domain.service.ReservaService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController

public class ReservaController implements ReservaApi {

    private final ObjectMapper objectMapper;
    private final ReservaService reservaService;

    @Autowired
    public ReservaController(ObjectMapper objectMapper, ReservaService reservaService) {
        this.objectMapper = objectMapper;
        this.reservaService = reservaService;
    }

    @Override
    public ResponseEntity<ReservaResponse> criarNovaReserva(ReservaRequest request) {
        Reserva reserva = objectMapper.convertValue(request, Reserva.class);
        Reserva reservaCriada = reservaService.criarNovaReserva(reserva, request.getCarroId(),request.getUsuarioId());
        ReservaResponse response = objectMapper.convertValue(reservaCriada,ReservaResponse.class);
        return ResponseEntity.ok(response);
    }

    @Override
    public ResponseEntity<ReservaResponse> consultarReservaPorId(UUID id) {
        Reserva reserva = reservaService.consultarReservaPorId(id);
        ReservaResponse response = ReservaResponseByReserva(reserva);
        return ResponseEntity.ok(response);
    }

    private ReservaResponse ReservaResponseByReserva(Reserva reserva) {
        return objectMapper.convertValue(reserva, ReservaResponse.class);
    }
}
