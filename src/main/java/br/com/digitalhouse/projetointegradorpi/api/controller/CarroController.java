package br.com.digitalhouse.projetointegradorpi.api.controller;

import br.com.digitalhouse.projetointegradorpi.api.CarroApi;
import br.com.digitalhouse.projetointegradorpi.api.dto.request.CarroRequest;
import br.com.digitalhouse.projetointegradorpi.api.dto.response.CarroResponse;
import br.com.digitalhouse.projetointegradorpi.api.dto.response.listResponse.CarroListResponse;
import br.com.digitalhouse.projetointegradorpi.domain.entity.Carro;
import br.com.digitalhouse.projetointegradorpi.domain.filter.FiltroCarro;
import br.com.digitalhouse.projetointegradorpi.domain.service.CarroService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
public class CarroController implements CarroApi {

    private final ObjectMapper objectMapper;
    private final CarroService carroService;

    @Autowired
    public CarroController(ObjectMapper objectMapper, CarroService carroService) {
        this.objectMapper = objectMapper;
        this.carroService = carroService;
    }

    @Override
    public ResponseEntity<CarroResponse> criarCarro(CarroRequest request) {
        Carro carro = objectMapper.convertValue(request, Carro.class);
        Carro carroCriado = carroService.criarCarro(carro, request.getCategoriaId(), request.getCidadeId(), request.getCaracteristicaCarroId());
        CarroResponse response = objectMapper.convertValue(carroCriado, CarroResponse.class);
        return ResponseEntity.ok(response);
    }

    @Override
    public ResponseEntity<Page<CarroListResponse>> buscarCarros(Pageable page, FiltroCarro filtroCarro) {
        Page<Carro> carros = carroService.buscarCarros(page, filtroCarro);
        Page<CarroListResponse> map = carros.map(carro -> new CarroListResponse(carro.getId(), carro.getModelo(), carro.getDescricao()));
        return ResponseEntity.ok(map);
    }

    @Override
    public ResponseEntity<CarroResponse> buscarCarroPorId(UUID id) {
        Carro carro = carroService.buscarCarroPorId(id);
        CarroResponse response = carroResponseByCarro(carro);
        return ResponseEntity.ok(response);
    }


    private CarroResponse carroResponseByCarro(Carro carro) {
        return objectMapper.convertValue(carro, CarroResponse.class);
    }
}
