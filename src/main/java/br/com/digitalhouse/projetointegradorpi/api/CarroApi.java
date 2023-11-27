package br.com.digitalhouse.projetointegradorpi.api;

import br.com.digitalhouse.projetointegradorpi.api.dto.openapi.PageExempleOpenApi;
import br.com.digitalhouse.projetointegradorpi.api.dto.request.CarroRequest;
import br.com.digitalhouse.projetointegradorpi.api.dto.response.CarroResponse;
import br.com.digitalhouse.projetointegradorpi.api.dto.response.listResponse.CarroListResponse;
import br.com.digitalhouse.projetointegradorpi.domain.filter.FiltroCarro;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@Tag(name = "API Carros")
@RequestMapping("carros")
public interface CarroApi {

    @PostMapping
    ResponseEntity<CarroResponse> criarCarro(@RequestBody @Valid CarroRequest request);

    @GetMapping
    ResponseEntity<Page<CarroListResponse>> buscarCarros(@PageableDefault @Schema(implementation = PageExempleOpenApi.class)
                                                         Pageable page, FiltroCarro filtroCarro);

    @GetMapping("{id}")
    ResponseEntity<CarroResponse> buscarCarroPorId(@PathVariable UUID id);




}
