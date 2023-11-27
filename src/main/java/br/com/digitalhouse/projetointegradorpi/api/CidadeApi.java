package br.com.digitalhouse.projetointegradorpi.api;


import br.com.digitalhouse.projetointegradorpi.api.dto.request.CidadeRequest;
import br.com.digitalhouse.projetointegradorpi.api.dto.response.CidadeResponse;
import br.com.digitalhouse.projetointegradorpi.api.dto.response.listResponse.CidadeListResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name = "API Cidades")
@RequestMapping("cidades")
public interface CidadeApi {

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<CidadeResponse> criarCidade(@RequestBody @Valid CidadeRequest request);

    @GetMapping
    ResponseEntity<Page<CidadeListResponse>> buscarCidades(@PageableDefault Pageable page,
                                                           @RequestParam(required = false)
                                                           String termo);


}
