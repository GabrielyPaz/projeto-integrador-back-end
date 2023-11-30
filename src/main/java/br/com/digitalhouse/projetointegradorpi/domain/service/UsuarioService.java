package br.com.digitalhouse.projetointegradorpi.domain.service;

import br.com.digitalhouse.projetointegradorpi.domain.entity.Usuario;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.UUID;

public interface UsuarioService {

    UserDetailsService userDetailsService();


}
