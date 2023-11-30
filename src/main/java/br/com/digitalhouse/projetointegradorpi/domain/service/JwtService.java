package br.com.digitalhouse.projetointegradorpi.domain.service;

import org.springframework.security.core.userdetails.UserDetails;

public interface JwtService {

    String extraindoEmail(String token);

    String gerandoToken(UserDetails userDetails);

    boolean tokenValido(String token, UserDetails userDetails);
}
