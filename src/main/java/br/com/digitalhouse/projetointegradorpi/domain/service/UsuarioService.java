package br.com.digitalhouse.projetointegradorpi.domain.service;

import br.com.digitalhouse.projetointegradorpi.api.dto.request.UsuarioRequest;
import br.com.digitalhouse.projetointegradorpi.domain.entity.Usuario;

import java.util.UUID;

public interface UsuarioService {
     Usuario criarUsuario(Usuario usuario, UUID funcaoId) ;
}
