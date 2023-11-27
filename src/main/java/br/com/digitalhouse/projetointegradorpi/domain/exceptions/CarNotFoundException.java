package br.com.digitalhouse.projetointegradorpi.domain.exceptions;

import java.util.UUID;

public class CarNotFoundException extends RuntimeException {
    public CarNotFoundException(UUID id) {
        super("Id do carro não encontrado " + id);
    }
}
