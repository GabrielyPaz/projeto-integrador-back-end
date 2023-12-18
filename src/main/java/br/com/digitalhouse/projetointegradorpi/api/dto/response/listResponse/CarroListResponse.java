package br.com.digitalhouse.projetointegradorpi.api.dto.response.listResponse;

import br.com.digitalhouse.projetointegradorpi.api.dto.response.CidadeResponse;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.UUID;

@Getter
@AllArgsConstructor
public class CarroListResponse {

    private UUID id;
    private String modelo;
    private String descricao;
    private CidadeResponse cidade;
}
