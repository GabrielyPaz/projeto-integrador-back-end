package br.com.digitalhouse.projetointegradorpi.domain.exceptions;

import java.util.UUID;

public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(UUID id) {
        super("Id do usuario não encontrado " + id);
    }
}
