package br.com.digitalhouse.projetointegradorpi.api.dto.request;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonPropertyOrder({"nome", "sobrenome", "email", "senha", "funcaoId"})
public class UsuarioRequest {

    @NotBlank
    @Schema(example = "Gabriely")
    private String nome;

    @NotBlank
    @Schema(example = "Paz")
    private String sobrenome;

    @Email
    @NotBlank
    @Schema(example = "gabriely.paz@email.com")
    private String email;

    @NotBlank
    @Schema(example = "Ptr$Ld12")
    private String senha;

    @NotBlank
    @Schema(example = "ADMIN")
    private String nomeFuncao = "USUARIO";

}
