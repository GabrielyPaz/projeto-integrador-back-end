package br.com.digitalhouse.projetointegradorpi.api.dto.response;

import br.com.digitalhouse.projetointegradorpi.domain.entity.IconeUrlEnum;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class CaracteristicaResponse {

    private UUID id;
    private String nome;
    private IconeUrlEnum iconeUrl;

}
