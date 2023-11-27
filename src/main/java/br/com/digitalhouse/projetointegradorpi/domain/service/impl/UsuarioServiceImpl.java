package br.com.digitalhouse.projetointegradorpi.domain.service.impl;

import br.com.digitalhouse.projetointegradorpi.domain.entity.Funcao;
import br.com.digitalhouse.projetointegradorpi.domain.entity.Usuario;
import br.com.digitalhouse.projetointegradorpi.domain.repository.FuncaoRepository;
import br.com.digitalhouse.projetointegradorpi.domain.repository.UsuarioRepository;
import br.com.digitalhouse.projetointegradorpi.domain.service.JwtService;
import br.com.digitalhouse.projetointegradorpi.domain.service.UsuarioService;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@AllArgsConstructor
public class UsuarioServiceImpl implements UsuarioService {
    private final UsuarioRepository usuarioRepository;

    @Override
    public UserDetailsService userDetailsService() {
        return (email) -> usuarioRepository.findByEmail(email)
                        .orElseThrow(() -> new UsernameNotFoundException("usuario não encontrado"));
    }

}
