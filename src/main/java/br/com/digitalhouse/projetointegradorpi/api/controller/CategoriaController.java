package br.com.digitalhouse.projetointegradorpi.api.controller;

import br.com.digitalhouse.projetointegradorpi.api.CategoriaApi;
import br.com.digitalhouse.projetointegradorpi.api.dto.request.CategoriaRequest;
import br.com.digitalhouse.projetointegradorpi.api.dto.response.listResponse.CategoriaListResponse;
import br.com.digitalhouse.projetointegradorpi.api.dto.response.CategoriaResponse;
import br.com.digitalhouse.projetointegradorpi.api.dto.response.wrapperResponse.CategoriaWrapperResponse;
import br.com.digitalhouse.projetointegradorpi.domain.entity.Categoria;
import br.com.digitalhouse.projetointegradorpi.domain.service.CategoriaService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
public class CategoriaController implements CategoriaApi {
    private final ObjectMapper objectMapper;
    private final CategoriaService categoriaService;

    public CategoriaController(ObjectMapper objectMapper, CategoriaService categoriaService) {
        this.objectMapper = objectMapper;
        this.categoriaService = categoriaService;
    }

    @Override
    public ResponseEntity<CategoriaResponse> criarCategoria(CategoriaRequest request) {
        Categoria categoria = objectMapper.convertValue(request, Categoria.class);
        Categoria categoriaCriada = categoriaService.criarCategoria(categoria);
        CategoriaResponse response = objectMapper.convertValue(categoriaCriada, CategoriaResponse.class);
        return ResponseEntity.ok(response);
    }

    @Override
    public ResponseEntity<Page<CategoriaListResponse>> buscarCategorias(Pageable page, String termo) {
        Page<Categoria> categorias = categoriaService.buscarCategorias(page, termo);
        Page<CategoriaListResponse> map = categorias.map(categoria -> new CategoriaListResponse(categoria.getId(),categoria.getNome(),categoria.getQualificacao()));
        return ResponseEntity.ok(map);
    }

    @Override
    public ResponseEntity<CategoriaResponse> buscarCategoriasPorId(UUID id) {
        Categoria categoria = categoriaService.buscarCategoriaPorId(id);
        CategoriaResponse response = categoriaResponseByCategoria(categoria);

        return ResponseEntity.ok(response);
    }

    @Override
    public ResponseEntity<CategoriaResponse> atualizarCategoria(UUID id, CategoriaRequest request) {
        Categoria categoria = objectMapper.convertValue(request,Categoria.class);
        Categoria categoriaAtualizada = categoriaService.atualizarCategoria(id,categoria);
        CategoriaResponse response = objectMapper.convertValue(categoriaAtualizada,CategoriaResponse.class);
        return ResponseEntity.ok(response);
    }

    @Override
    public ResponseEntity<Void> deletarCategoria(UUID id) {
        categoriaService.deletarCategoria(id);
        return ResponseEntity.ok().build();
    }

    private CategoriaResponse categoriaResponseByCategoria(Categoria categoria) {
        return objectMapper.convertValue(categoria, CategoriaResponse.class);
    }
}
