package br.com.digitalhouse.projetointegradorpi.api.dto.response.listResponse;

import br.com.digitalhouse.projetointegradorpi.domain.entity.IconeUrlEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.UUID;

@Getter
@AllArgsConstructor
public class CaracteristicaListResponse {

    private UUID id;
    private String nome;
    private IconeUrlEnum iconeUrl;
}
