package br.com.digitalhouse.projetointegradorpi.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum IconeUrlEnum {
    
    URL1("Ar condicionado"),
    URL2("Portas"),
    URL3("Direção Hidráulica"),
    URL4("Câmbio Automático"),
    URL5("Câmbio Manual");
    
    private String nomeUrl;
}
