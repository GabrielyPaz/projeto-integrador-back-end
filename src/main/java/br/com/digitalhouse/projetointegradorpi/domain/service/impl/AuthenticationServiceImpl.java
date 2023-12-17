package br.com.digitalhouse.projetointegradorpi.domain.service.impl;

import br.com.digitalhouse.projetointegradorpi.domain.entity.Funcao;
import br.com.digitalhouse.projetointegradorpi.domain.entity.Usuario;
import br.com.digitalhouse.projetointegradorpi.domain.exceptions.UserAlreadyExistsException;
import br.com.digitalhouse.projetointegradorpi.domain.repository.FuncaoRepository;
import br.com.digitalhouse.projetointegradorpi.domain.repository.UsuarioRepository;
import br.com.digitalhouse.projetointegradorpi.domain.service.AuthenticationService;
import br.com.digitalhouse.projetointegradorpi.domain.service.JwtService;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@AllArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {
    private final UsuarioRepository usuarioRepository;
    private final FuncaoRepository funcaoRepository;

    private final AuthenticationManager authenticationManager;
    private final PasswordEncoder passwordEncoder;

    private final JwtService jwtService;

    @Override
    public String login(String email, String senha) {
            var autenticacao = new UsernamePasswordAuthenticationToken(email, senha);
            authenticationManager.authenticate(autenticacao);
            var usuario = usuarioRepository
                    .findByEmail(email)
                    .orElseThrow(() -> new IllegalArgumentException("Email ou senha invalido."));
            return jwtService.gerandoToken(usuario);
    }

    @Override
    public Usuario criarUsuario(Usuario usuario, String nomeFuncao) {
        usuarioRepository.findByEmail(usuario.getEmail())
                .ifPresent(usuarioExistente -> {
                    throw new UserAlreadyExistsException(usuario.getEmail());
                });
       Funcao funcao = funcaoRepository.findFuncaoByNome(nomeFuncao)
                .orElse(funcaoRepository.save(new Funcao(nomeFuncao)));
        usuario.setFuncao(funcao);

        String senhaCodificada = passwordEncoder.encode(usuario.getSenha());
        usuario.setSenha(senhaCodificada);

        return usuarioRepository.save(usuario);
    }
}
