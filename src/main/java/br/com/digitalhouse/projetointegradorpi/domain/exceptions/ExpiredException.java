package br.com.digitalhouse.projetointegradorpi.domain.exceptions;

import java.util.UUID;

public class ExpiredException extends RuntimeException {
    public ExpiredException() {
        super("Jwt expirado!", null, true, false);
    }
}
