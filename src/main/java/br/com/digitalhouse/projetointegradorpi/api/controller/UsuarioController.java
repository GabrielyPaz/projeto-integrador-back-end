package br.com.digitalhouse.projetointegradorpi.api.controller;

import br.com.digitalhouse.projetointegradorpi.api.UsuarioApi;
import br.com.digitalhouse.projetointegradorpi.api.dto.request.UsuarioRequest;
import br.com.digitalhouse.projetointegradorpi.api.dto.response.UsuarioResponse;
import br.com.digitalhouse.projetointegradorpi.domain.entity.Usuario;
import br.com.digitalhouse.projetointegradorpi.domain.service.UsuarioService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UsuarioController implements UsuarioApi {

    private final ObjectMapper objectMapper;
    private final UsuarioService usuarioService;

    @Autowired
    public UsuarioController(ObjectMapper objectMapper, UsuarioService usuarioService) {
        this.objectMapper = objectMapper;
        this.usuarioService = usuarioService;
    }

    @Override
    public ResponseEntity<UsuarioResponse> CriarUsuario(UsuarioRequest request) {
        Usuario usuario = objectMapper.convertValue(request, Usuario.class);
        Usuario usuarioCriado = usuarioService.criarUsuario(usuario,request.getFuncaoId());
        UsuarioResponse response = objectMapper.convertValue(usuarioCriado, UsuarioResponse.class);
        return ResponseEntity.ok(response);
    }
}
