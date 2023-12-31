package br.com.digitalhouse.projetointegradorpi.api.dto.response;

import br.com.digitalhouse.projetointegradorpi.domain.entity.FotoCarroEnum;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;
import java.util.UUID;

@Getter
@Setter

public class CarroResponse {

    private UUID id;
    private String modelo;
    private String descricao;
    private Set<CaracteristicaResponse> caracteristicasCarro;
    private FotoCarroEnum fotoCarro;
    private CategoriaResponse categoria;
    private CidadeResponse cidade;
}
