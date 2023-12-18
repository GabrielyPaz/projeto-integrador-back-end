package br.com.digitalhouse.projetointegradorpi.domain.exceptions;

import java.util.UUID;

public class ReservaAlreadyExistsException extends RuntimeException{
    public ReservaAlreadyExistsException() {
        super("Reserva indisponivel para o periodo informado ",  null, true, false);
    }



}
