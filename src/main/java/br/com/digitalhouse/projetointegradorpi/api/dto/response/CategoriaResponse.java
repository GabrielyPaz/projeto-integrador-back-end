package br.com.digitalhouse.projetointegradorpi.api.dto.response;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter

public class CategoriaResponse {

    private UUID id;
    private String qualificacao;
    private String descricao;
    private String urlImagem;
}
