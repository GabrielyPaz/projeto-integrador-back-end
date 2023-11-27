package br.com.digitalhouse.projetointegradorpi.api.dto.request;


import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CidadeRequest {

    @NotBlank
    @Schema(example = "Niteroi")
    private String nome;
    @NotBlank
    @Length(min = 2, max = 2)
    @Schema(example = "RJ")
    private String estado;

}
