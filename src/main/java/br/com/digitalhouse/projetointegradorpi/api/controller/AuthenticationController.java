package br.com.digitalhouse.projetointegradorpi.api.controller;


import br.com.digitalhouse.projetointegradorpi.api.dto.request.AutenticacaoRequest;
import br.com.digitalhouse.projetointegradorpi.api.dto.response.AuthenticationResponse;
import br.com.digitalhouse.projetointegradorpi.domain.service.AuthenticationService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@Tag(name = "API Usuarios")
@RequestMapping("authentication")
public class AuthenticationController {
    private final AuthenticationService authenticationService;

    @PostMapping(value = "/login")
    public ResponseEntity<AuthenticationResponse> createAuthenticationToken(@RequestBody @Valid AutenticacaoRequest request){
        String jwt = authenticationService.login(request.getEmail(), request.getSenha());
        return ResponseEntity.ok(new AuthenticationResponse(jwt));
    }
}
