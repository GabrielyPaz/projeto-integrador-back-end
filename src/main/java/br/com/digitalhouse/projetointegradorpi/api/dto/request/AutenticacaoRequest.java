package br.com.digitalhouse.projetointegradorpi.api.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class AutenticacaoRequest {
    @NotBlank
    private String email;
    @NotBlank
    private String senha;
}
