package br.com.digitalhouse.projetointegradorpi.domain.service.impl;

import br.com.digitalhouse.projetointegradorpi.domain.service.JwtService;
import br.com.digitalhouse.projetointegradorpi.infrastructure.configuration.JwtUtil;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service

public class JwtServiceImpl implements JwtService {

    private final JwtUtil jwtUtil;

    public JwtServiceImpl(JwtUtil jwtUtil) {
        this.jwtUtil = jwtUtil;
    }

    @Override
    public String extraindoEmail(String token) {
        return jwtUtil.extractUserName(token);
    }

    @Override
    public String gerandoToken(UserDetails userDetails) {
        return jwtUtil.generateToken(userDetails);
    }

    @Override
    public boolean tokenValido(String token, UserDetails userDetails) {
        return jwtUtil.validateToken(token, userDetails);
    }
}
