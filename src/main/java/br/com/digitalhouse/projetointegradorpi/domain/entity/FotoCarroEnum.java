package br.com.digitalhouse.projetointegradorpi.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum FotoCarroEnum {

    HATCH("url1"),
    SEDAN("url2"),
    SUV("url3"),
    IMPORTADO("url4");

    private String urlFotoCarro;

    // CONSIDEREI A IMAGEM DO CARRO ENUM
    // POIS SÓ HAVERIA UMA FOTO

}
