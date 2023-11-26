package br.com.digitalhouse.projetointegradorpi.api.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class UsuarioRequest {

    @NotBlank
    @Schema(example = "Gabriely")
    private String nome;
    @NotBlank
    @Schema(example = "Paz")
    private String Sobrenome;
    @Email
    private String email;
    private String senha;
    private UUID funcaoId;
   }
