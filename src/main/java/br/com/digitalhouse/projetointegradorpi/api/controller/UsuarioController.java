package br.com.digitalhouse.projetointegradorpi.api.controller;

import br.com.digitalhouse.projetointegradorpi.api.UsuarioApi;
import br.com.digitalhouse.projetointegradorpi.api.dto.request.UsuarioRequest;
import br.com.digitalhouse.projetointegradorpi.api.dto.response.CreateUsuarioResponse;
import br.com.digitalhouse.projetointegradorpi.api.dto.response.ReservaResponse;
import br.com.digitalhouse.projetointegradorpi.domain.entity.Reserva;
import br.com.digitalhouse.projetointegradorpi.domain.entity.Usuario;
import br.com.digitalhouse.projetointegradorpi.domain.service.AuthenticationService;
import br.com.digitalhouse.projetointegradorpi.domain.service.JwtService;
import br.com.digitalhouse.projetointegradorpi.domain.service.UsuarioService;
import br.com.digitalhouse.projetointegradorpi.infrastructure.mapper.ReservaMapper;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@AllArgsConstructor
public class UsuarioController implements UsuarioApi {

    private final AuthenticationService authenticationService;

    private final UsuarioService usuarioService;

    private final ObjectMapper objectMapper;

    private final JwtService jwtService;

    private final ReservaMapper reservaMapper;

    @Override
    public ResponseEntity<CreateUsuarioResponse> criarUsuario(UsuarioRequest request) {
        Usuario usuario = objectMapper.convertValue(request, Usuario.class);
        Usuario usuarioCriado = authenticationService.criarUsuario(usuario, request.getNomeFuncao());
        CreateUsuarioResponse response = objectMapper.convertValue(usuarioCriado, CreateUsuarioResponse.class);
        String token = jwtService.gerandoToken(usuarioCriado);
        response.setJwt(token);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @Override
    public ResponseEntity <Page<ReservaResponse>> buscarReservasPorUsuario(UUID id, Pageable page) {
        Page<Reserva> reservas = usuarioService.buscarReservasUsuario(id, page);
        Page<ReservaResponse> reservaResponses = reservas.map(reservaMapper::reservaParaReservaResponse);
        return ResponseEntity.ok(reservaResponses);
    }
}
