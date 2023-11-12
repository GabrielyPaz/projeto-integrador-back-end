package br.com.digitalhouse.projetointegradorpi.api;

import br.com.digitalhouse.projetointegradorpi.api.dto.request.CarroRequest;
import br.com.digitalhouse.projetointegradorpi.api.dto.response.CarroResponse;
import br.com.digitalhouse.projetointegradorpi.api.dto.response.CidadeResponse;
import br.com.digitalhouse.projetointegradorpi.api.dto.response.listResponse.CarroListResponse;
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
    ResponseEntity<Page<CarroListResponse>> buscarCarros(@PageableDefault Pageable page,
                                                        @RequestParam(required = false)
                                                             String termo);
    @GetMapping("{id}")
    ResponseEntity<CarroResponse> buscarCarroPorId(@PathVariable UUID id);


    // ------- Falta configurar este endpoint abaixo referente a busca de carros por cidade ou categoria -------
    @GetMapping("{Cidade}") // Implementar busca de produtos por cidade ou Categoria -- arrumar esse endpoint--
    ResponseEntity<CidadeResponse>buscarCarrosPorCidade();


    // NÃO ENTENDI ESSE
    //ResponseEntity<CarroResponse> criarCarro(CarroRequest request);
}
