package br.com.digitalhouse.projetointegradorpi.domain.filter;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
public class FiltroCarro {
    private String termo;
    private String cidade;
    private LocalDateTime dataInicial;
    private LocalDateTime dataFinal;
}
