package br.com.digitalhouse.projetointegradorpi.api.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
public class ReservaResponse {
    private UUID id;
    @JsonFormat(pattern = "dd/MM/yyyy HH:mm")
    private LocalDateTime dataInicial;
    @JsonFormat(pattern = "dd/MM/yyyy HH:mm")
    private LocalDateTime dataFinal;
    private CarroResponse carro;
    private UsuarioResponse usuario;
}
