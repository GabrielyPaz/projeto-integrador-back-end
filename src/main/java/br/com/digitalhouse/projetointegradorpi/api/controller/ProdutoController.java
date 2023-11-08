package br.com.digitalhouse.projetointegradorpi.api.controller;

import br.com.digitalhouse.projetointegradorpi.api.ProdutoApi;
import br.com.digitalhouse.projetointegradorpi.api.dto.request.ProdutoRequest;
import br.com.digitalhouse.projetointegradorpi.api.dto.response.CategoriaResponse;
import br.com.digitalhouse.projetointegradorpi.api.dto.response.CidadeResponse;
import br.com.digitalhouse.projetointegradorpi.api.dto.response.ProdutoResponse;
import br.com.digitalhouse.projetointegradorpi.api.dto.response.listResponse.ProdutoListResponse;
import br.com.digitalhouse.projetointegradorpi.domain.entity.Categoria;
import br.com.digitalhouse.projetointegradorpi.domain.service.ProdutoService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.coyote.Response;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
public class ProdutoController implements ProdutoApi {

    private final ObjectMapper objectMapper;
    private final ProdutoService produtoService;

    public ProdutoController(ObjectMapper objectMapper, ProdutoService produtoService) {
        this.objectMapper = objectMapper;
        this.produtoService = produtoService;
    }

    @Override
    public ResponseEntity<ProdutoResponse> criarProduto(ProdutoRequest request) {
        Produto produto = objectMapper.convertValue(request, Produto.class);
        Produto produtoCriado = produtoService.criarProduto(produto);
        ProdutoResponse response = objectMapper.convertValue(produtoCriado,ProdutoResponse.class);
        return ResponseEntity.ok(response);
    }

  //-------- precisa inserir no endpoint abaixo os campos List<caracteristicas>, List <Imagens>, Categoria e Cidade, referentes aos relacionamentos das tabelas-------

    @Override
    public ResponseEntity<Page<ProdutoListResponse>> buscarProdutos(Pageable page, String termo) {
        Page<Produto>produtos = produtoService.buscarProdutos(page,termo);
        Page<ProdutoListResponse> map = produtos.map(produto -> new ProdutoListResponse(produto.getId,produto.getModelo,produto.getDescricao));
        return ResponseEntity.ok(map);
    }

    @Override
    public ResponseEntity<ProdutoResponse> buscarProdutosPorId(UUID id) {
        Produto produto = produtoService.buscarProdutosPorId(id);
        ProdutoResponse response = produtoResponseByProduto(produto);
        return ResponseEntity.ok(response);
    }

    // ------- Falta configurar este endpoint abaixo referente a busca de produtos por cidade ou categoria -------
    @Override
    public ResponseEntity<CidadeResponse> buscarProdutosPorCidade() {
        return null;
    }

    private ProdutoResponse produtoResponseByProduto(Produto produto) {
        return objectMapper.convertValue(produto, ProdutoResponse.class);
    }
}
