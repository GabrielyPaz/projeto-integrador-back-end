package br.com.digitalhouse.projetointegradorpi.api;

import br.com.digitalhouse.projetointegradorpi.api.dto.request.ReservaRequest;
import br.com.digitalhouse.projetointegradorpi.api.dto.response.ReservaResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@Tag(name = "API Reservas")
@RequestMapping("reservas")
public interface ReservaApi {

    @PostMapping
    ResponseEntity<ReservaResponse> criarNovaReserva(@RequestBody @Valid ReservaRequest request);


    @GetMapping("{id}")
    ResponseEntity<ReservaResponse> consultarReservaPorId(@PathVariable UUID id);
}
