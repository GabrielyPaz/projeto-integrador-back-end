package br.com.digitalhouse.projetointegradorpi.api.dto.response;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
public class ReservaResponse {
    private UUID id;
    private LocalDateTime dataInicial;
    private LocalDateTime dataFinal;
    private CarroResponse carro;
    private UsuarioResponse usuario;
}
