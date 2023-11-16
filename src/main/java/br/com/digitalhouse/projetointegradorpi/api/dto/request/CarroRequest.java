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
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class CarroRequest {

    @NotBlank
    @Schema(example = "Corolla")
    private String modelo;
    @NotBlank
    @Schema(example = "Carro sedan mais confortavel da toyota")
    private String descricao;
    private Set<UUID> caracteristicaCarroId;
    private FotoCarroEnum fotoCarro;
    private UUID categoriaId;
    private UUID cidadeId;
}
