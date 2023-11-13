package br.com.digitalhouse.projetointegradorpi.api.dto.response;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class CidadeResponse {

    private UUID id;
    private String nome;
    private String estado;
}
