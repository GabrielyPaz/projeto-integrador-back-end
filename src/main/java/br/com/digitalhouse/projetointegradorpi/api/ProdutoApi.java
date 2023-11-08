package br.com.digitalhouse.projetointegradorpi.api;

import br.com.digitalhouse.projetointegradorpi.api.dto.request.ProdutoRequest;
import br.com.digitalhouse.projetointegradorpi.api.dto.response.CidadeResponse;
import br.com.digitalhouse.projetointegradorpi.api.dto.response.ProdutoResponse;
import br.com.digitalhouse.projetointegradorpi.api.dto.response.listResponse.ProdutoListResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.UUID;

@Tag(name = "API Produtos")
@RequestMapping("produtos")
public interface ProdutoApi {

    @PostMapping
    ResponseEntity<ProdutoResponse> criarProduto(@RequestBody @Valid ProdutoRequest request);

    @GetMapping
    ResponseEntity<Page<ProdutoListResponse>> buscarProdutos(@PageableDefault Pageable page,
                                                             @RequestParam(required = false)
                                                             String termo);
    @GetMapping("{id}")
    ResponseEntity<ProdutoResponse> buscarProdutosPorId(@PathVariable UUID id);

    @GetMapping("{Cidade}") // Implementar busca de produtos por cidade ou Categoria -- arrumar esse endpoint--
    ResponseEntity<CidadeResponse>buscarProdutosPorCidade();

}
