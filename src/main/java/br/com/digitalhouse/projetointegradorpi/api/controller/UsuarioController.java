package br.com.digitalhouse.projetointegradorpi.api.controller;

import br.com.digitalhouse.projetointegradorpi.api.UsuarioApi;
import br.com.digitalhouse.projetointegradorpi.api.dto.request.UsuarioRequest;
import br.com.digitalhouse.projetointegradorpi.api.dto.response.UsuarioResponse;
import br.com.digitalhouse.projetointegradorpi.domain.entity.Usuario;
import br.com.digitalhouse.projetointegradorpi.domain.service.AuthenticationService;
import br.com.digitalhouse.projetointegradorpi.domain.service.JwtService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class UsuarioController implements UsuarioApi {
    private final AuthenticationService authenticationService;
    private final ObjectMapper objectMapper;
    private final JwtService jwtService;

    @Override
    public ResponseEntity<UsuarioResponse> criarUsuario(UsuarioRequest request) {
        Usuario usuario = objectMapper.convertValue(request, Usuario.class);
        Usuario usuarioCriado = authenticationService.criarUsuario(usuario, request.getNomeFuncao());
        UsuarioResponse response = objectMapper.convertValue(usuarioCriado, UsuarioResponse.class);
        String token = jwtService.gerandoToken(usuarioCriado);
        response.setJwt(token);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}
