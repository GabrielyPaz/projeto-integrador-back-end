package br.com.digitalhouse.projetointegradorpi.domain.service;

import br.com.digitalhouse.projetointegradorpi.domain.entity.Usuario;

import java.util.UUID;

public interface AuthenticationService {
    String login(String email, String senha);

    Usuario criarUsuario(Usuario usuario/*, String nomeFuncao*/);

}
