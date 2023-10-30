package br.com.digitalhouse.projetointegradorpi.api;

import br.com.digitalhouse.projetointegradorpi.api.dto.request.CategoriaRequest;
import br.com.digitalhouse.projetointegradorpi.api.dto.response.CategoriaResponse;
import br.com.digitalhouse.projetointegradorpi.api.dto.response.wrapperResponse.CategoriaWrapperResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@Tag(name= "API Categorias")
@RequestMapping("categorias")
public interface CategoriaApi {

    @PostMapping
    ResponseEntity<CategoriaResponse> criarCategoria(@RequestBody @Valid CategoriaRequest request);

    @GetMapping
    ResponseEntity<CategoriaWrapperResponse> buscarCategorias(@RequestParam String nome);

    @PutMapping( "{id}")

    ResponseEntity<CategoriaResponse> atualizarCategoria(@PathVariable UUID id, @RequestBody @Valid CategoriaRequest request);

    @DeleteMapping( "{id}")
    ResponseEntity<Void> deletarCategoria(@PathVariable UUID id);

}


