package br.com.digitalhouse.projetointegradorpi.domain.exceptions;

import java.util.UUID;

public class CategoryNotFoundException extends RuntimeException {
    public CategoryNotFoundException(UUID id) {
        super("Id da categoria não encontrado" + id);
    }
}
