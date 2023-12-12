package br.com.digitalhouse.projetointegradorpi.api;

import br.com.digitalhouse.projetointegradorpi.api.dto.request.UsuarioRequest;
import br.com.digitalhouse.projetointegradorpi.api.dto.response.CreateUsuarioResponse;
import br.com.digitalhouse.projetointegradorpi.api.dto.response.ReservaResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@Tag(name = "API Usuarios")
@RequestMapping("usuarios")
public interface UsuarioApi {
    @PostMapping
    ResponseEntity<CreateUsuarioResponse> criarUsuario(@RequestBody @Valid UsuarioRequest request);

    @GetMapping("{id}/reservas")
    ResponseEntity<Page<ReservaResponse>> buscarReservasPorUsuario(@PathVariable UUID id, Pageable page);
}
