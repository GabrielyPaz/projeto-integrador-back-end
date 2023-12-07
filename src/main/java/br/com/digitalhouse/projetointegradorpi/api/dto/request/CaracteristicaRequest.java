package br.com.digitalhouse.projetointegradorpi.api.dto.request;

import br.com.digitalhouse.projetointegradorpi.domain.entity.IconeUrlEnum;
import io.swagger.v3.oas.annotations.media.Schema;
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
public class CaracteristicaRequest {

    @NotBlank
    @Schema(example = "ar condicionado")
    private String nome;
    @NotNull
    private IconeUrlEnum iconeUrl;


}
