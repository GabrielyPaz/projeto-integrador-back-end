package br.com.digitalhouse.projetointegradorpi.api.dto.response.listResponse;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
public class CategoriaListResponse {

    private UUID id;
    private String nome;
    private String qualificacao;

}
