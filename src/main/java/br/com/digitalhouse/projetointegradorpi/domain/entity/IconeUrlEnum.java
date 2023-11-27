package br.com.digitalhouse.projetointegradorpi.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum IconeUrlEnum {

    AR_CONDICIONADO("url1"),
    PORTAS("url2"),
    DIRECAO_HIDRAULICA("url3"),
    AUTOMATICO("url4"),
    MANUAL("url5");

    private String nomeUrl;
}
