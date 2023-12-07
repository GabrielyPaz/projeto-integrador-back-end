package br.com.digitalhouse.projetointegradorpi.api;


import br.com.digitalhouse.projetointegradorpi.api.dto.request.CaracteristicaRequest;
import br.com.digitalhouse.projetointegradorpi.api.dto.response.CaracteristicaResponse;
import br.com.digitalhouse.projetointegradorpi.api.dto.response.listResponse.CaracteristicaListResponse;
import br.com.digitalhouse.projetointegradorpi.api.dto.response.listResponse.CategoriaListResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name = "API Caracteristicas")
@RequestMapping("caracteristicas")
public interface CaracteristicaApi {

    @PostMapping
    ResponseEntity<CaracteristicaResponse>criarCaracteristica(@RequestBody @Valid CaracteristicaRequest request);


    @GetMapping
    ResponseEntity<Page<CaracteristicaListResponse>> buscarCaracteristica(@PageableDefault Pageable page, @RequestParam(required = false) String termo);


}
