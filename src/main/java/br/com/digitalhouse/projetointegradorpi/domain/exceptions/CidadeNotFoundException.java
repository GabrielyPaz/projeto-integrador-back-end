package br.com.digitalhouse.projetointegradorpi.domain.exceptions;

import java.util.UUID;

public class CidadeNotFoundException extends RuntimeException {
    public CidadeNotFoundException(UUID cidadeId) {
        super ("Id da cidade não encontrado " + cidadeId);
    }
}
