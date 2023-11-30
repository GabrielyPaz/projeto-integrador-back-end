package br.com.digitalhouse.projetointegradorpi.domain.filter;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class FiltroVeiculo {
    private String termo;
    private String cidade;
    private LocalDateTime dataInicial;
    private LocalDateTime dataFinal;
}
