package br.com.digitalhouse.projetointegradorpi.api.controller;

import br.com.digitalhouse.projetointegradorpi.api.CaracteristicaApi;
import br.com.digitalhouse.projetointegradorpi.api.dto.request.CaracteristicaRequest;
import br.com.digitalhouse.projetointegradorpi.api.dto.response.CaracteristicaResponse;
import br.com.digitalhouse.projetointegradorpi.api.dto.response.listResponse.CaracteristicaListResponse;
import br.com.digitalhouse.projetointegradorpi.api.dto.response.listResponse.CidadeListResponse;
import br.com.digitalhouse.projetointegradorpi.domain.entity.Caracteristica;
import br.com.digitalhouse.projetointegradorpi.domain.entity.Cidade;
import br.com.digitalhouse.projetointegradorpi.domain.service.CaracteristicaService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController

public class CaracteristicaController implements CaracteristicaApi {

    private final ObjectMapper objectMapper;
    private final CaracteristicaService caracteristicaService;

    public CaracteristicaController(ObjectMapper objectMapper, CaracteristicaService caracteristicaService) {
        this.objectMapper = objectMapper;
        this.caracteristicaService = caracteristicaService;
    }

    @Override
    public ResponseEntity<CaracteristicaResponse> criarCaracteristica(CaracteristicaRequest request) {
        Caracteristica caracteristica = objectMapper.convertValue(request, Caracteristica.class);
        Caracteristica caracteristicaCriada = caracteristicaService.criarCaracteristica(caracteristica);
        CaracteristicaResponse response = objectMapper.convertValue(caracteristicaCriada, CaracteristicaResponse.class);
        return ResponseEntity.ok(response);
    }

    @Override
    public ResponseEntity<Page<CaracteristicaListResponse>> buscarCaracteristica(Pageable page, String termo) {
        Page<Caracteristica> caracteristicas = caracteristicaService.buscarCaracteristica(page, termo);
        Page<CaracteristicaListResponse> map = caracteristicas.map(caracteristica -> new CaracteristicaListResponse(caracteristica.getId(), caracteristica.getNome(), caracteristica.getIconeUrl()));
        return ResponseEntity.ok(map);
    }

}
