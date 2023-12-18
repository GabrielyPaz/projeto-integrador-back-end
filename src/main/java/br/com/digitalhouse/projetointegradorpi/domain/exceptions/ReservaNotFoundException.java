package br.com.digitalhouse.projetointegradorpi.domain.exceptions;

import java.util.UUID;

public class ReservaNotFoundException extends RuntimeException{
    public ReservaNotFoundException(UUID id) {
        super("Id da reserva não encontrada" + id,  null, true, false);
    }



}
