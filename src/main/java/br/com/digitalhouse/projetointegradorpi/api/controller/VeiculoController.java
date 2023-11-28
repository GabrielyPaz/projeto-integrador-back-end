package br.com.digitalhouse.projetointegradorpi.api.controller;

import br.com.digitalhouse.projetointegradorpi.api.CarroApi;
import br.com.digitalhouse.projetointegradorpi.api.dto.request.VeiculoRequest;
import br.com.digitalhouse.projetointegradorpi.api.dto.response.CarroResponse;
import br.com.digitalhouse.projetointegradorpi.api.dto.response.listResponse.CarroListResponse;
import br.com.digitalhouse.projetointegradorpi.domain.entity.Veiculo;
import br.com.digitalhouse.projetointegradorpi.domain.filter.FiltroVeiculo;
import br.com.digitalhouse.projetointegradorpi.domain.service.VeiculoService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
public class VeiculoController implements CarroApi {

    private final ObjectMapper objectMapper;
    private final VeiculoService veiculoService;

    @Autowired
    public VeiculoController(ObjectMapper objectMapper, VeiculoService veiculoService) {
        this.objectMapper = objectMapper;
        this.veiculoService = veiculoService;
    }

    @Override
    public ResponseEntity<CarroResponse> criarCarro(VeiculoRequest request) {
        Veiculo veiculo = objectMapper.convertValue(request, Veiculo.class);
        Veiculo veiculoCriado = veiculoService.criarCarro(veiculo, request.getCategoriaId(), request.getCidadeId(), request.getCaracteristicaCarroId());
        CarroResponse response = objectMapper.convertValue(veiculoCriado, CarroResponse.class);
        return ResponseEntity.ok(response);
    }

    @Override
    public ResponseEntity<Page<CarroListResponse>> buscarCarros(Pageable page, FiltroVeiculo filtroVeiculo) {
        Page<Veiculo> carros = veiculoService.buscarCarros(page, filtroVeiculo);
        Page<CarroListResponse> map = carros.map(carro -> new CarroListResponse(carro.getId(), carro.getModelo(), carro.getDescricao()));
        return ResponseEntity.ok(map);
    }

    @Override
    public ResponseEntity<CarroResponse> buscarCarroPorId(UUID id) {
        Veiculo veiculo = veiculoService.buscarCarroPorId(id);
        CarroResponse response = carroResponseByCarro(veiculo);
        return ResponseEntity.ok(response);
    }


    private CarroResponse carroResponseByCarro(Veiculo veiculo) {
        return objectMapper.convertValue(veiculo, CarroResponse.class);
    }
}
