package br.com.digitalhouse.projetointegradorpi.api;

import br.com.digitalhouse.projetointegradorpi.api.dto.request.CategoriaRequest;
import br.com.digitalhouse.projetointegradorpi.api.dto.response.CategoriaResponse;
import br.com.digitalhouse.projetointegradorpi.api.dto.response.listResponse.CategoriaListResponse;
import br.com.digitalhouse.projetointegradorpi.api.dto.response.wrapperResponse.CategoriaWrapperResponse;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@Tag(name= "API Categorias")
@RequestMapping("categorias")
public interface CategoriaApi {

    @PostMapping
    @ApiResponse(responseCode = "200", description = "Deu tudo certo por aqui",
            content = @Content(schema = @Schema(implementation = CategoriaResponse.class)))
    ResponseEntity<CategoriaResponse> criarCategoria(@RequestBody @Valid CategoriaRequest request);

    @GetMapping
    ResponseEntity<Page<CategoriaListResponse>> buscarCategorias(@PageableDefault Pageable page, @RequestParam(required = false) String termo);

    @GetMapping("{id}")
    ResponseEntity<CategoriaResponse> buscarCategoriasPorId(@PathVariable UUID id);

    @PutMapping( "{id}")

    ResponseEntity<CategoriaResponse> atualizarCategoria(@PathVariable UUID id, @RequestBody @Valid CategoriaRequest request);

    @DeleteMapping( "{id}")
    ResponseEntity<Void> deletarCategoria(@PathVariable UUID id);

}


