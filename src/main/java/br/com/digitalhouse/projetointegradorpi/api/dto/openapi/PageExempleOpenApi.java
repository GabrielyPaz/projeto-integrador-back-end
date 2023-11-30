package br.com.digitalhouse.projetointegradorpi.api.dto.openapi;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;

@Getter
public class PageExempleOpenApi {

    @Schema(example = "0")
    private Integer page;
    @Schema(example = "10")
    private Integer size;

}
