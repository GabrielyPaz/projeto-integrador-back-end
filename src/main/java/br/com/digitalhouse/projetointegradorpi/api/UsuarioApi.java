package br.com.digitalhouse.projetointegradorpi.api;

import br.com.digitalhouse.projetointegradorpi.api.dto.request.UsuarioRequest;
import br.com.digitalhouse.projetointegradorpi.api.dto.response.UsuarioResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Tag(name = "API Usuarios")
@RequestMapping("usuarios")
public interface UsuarioApi {
    @PostMapping
    ResponseEntity<UsuarioResponse>CriarUsuario(@RequestBody @Valid UsuarioRequest request);
}
