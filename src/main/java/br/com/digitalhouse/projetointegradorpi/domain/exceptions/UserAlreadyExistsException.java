package br.com.digitalhouse.projetointegradorpi.domain.exceptions;

import java.util.UUID;

public class UserAlreadyExistsException extends RuntimeException {

    public UserAlreadyExistsException(String email) {
        super("E-mail " + email + " já existente",  null, true, false);
    }

}
