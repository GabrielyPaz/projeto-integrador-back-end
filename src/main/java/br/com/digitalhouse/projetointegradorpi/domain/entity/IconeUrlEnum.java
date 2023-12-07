package br.com.digitalhouse.projetointegradorpi.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
public enum IconeUrlEnum {

    AR_CONDICIONADO("url1"),
    PORTAS("url2"),
    DIRECAO_HIDRAULICA("url3"),
    AUTOMATICO("url4"),
    MANUAL("url5");

    private final String nomeUrl;

    IconeUrlEnum(String nomeUrl) {
        this.nomeUrl = nomeUrl;
    }
}
