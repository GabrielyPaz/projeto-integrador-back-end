package br.com.digitalhouse.projetointegradorpi.api.dto.request;

import br.com.digitalhouse.projetointegradorpi.domain.entity.Caracteristica;
import br.com.digitalhouse.projetointegradorpi.domain.entity.Categoria;
import br.com.digitalhouse.projetointegradorpi.domain.entity.Cidade;
import br.com.digitalhouse.projetointegradorpi.domain.entity.FotoCarroEnum;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class CarroRequest {

    @NotBlank
    @Schema(example = "Corolla")
    private String modelo;
    @NotBlank
    @Schema(example = "modelo sedan mais confortavel da toyota")
    private String descricao;
    @NotBlank
    @Schema(example = "url do icone")
    private Set<Caracteristica> caracteristicasCarro;
    @NotBlank
    @Schema(example = "url do icone")
    private FotoCarroEnum fotoCarro;
    @NotBlank

    private Categoria categoria;
    @NotBlank
    private Cidade cidade;
}
