package br.com.digitalhouse.projetointegradorpi.api.dto.response;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@JsonPropertyOrder({"id", "nome", "sobrenome", "email", "senha", "funcao"})
public class UsuarioResponse {

    private UUID id;
    private String nome;
    private String Sobrenome;
    private String email;
    private FuncaoResponse funcao;
    private String jwt;
}
