package br.com.digitalhouse.projetointegradorpi.domain.service.impl;

import br.com.digitalhouse.projetointegradorpi.domain.entity.Reserva;
import br.com.digitalhouse.projetointegradorpi.domain.entity.Usuario;
import br.com.digitalhouse.projetointegradorpi.domain.exceptions.UserNotFoundException;
import br.com.digitalhouse.projetointegradorpi.domain.repository.ReservaRepository;
import br.com.digitalhouse.projetointegradorpi.domain.repository.UsuarioRepository;
import br.com.digitalhouse.projetointegradorpi.domain.service.UsuarioService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@AllArgsConstructor
public class UsuarioServiceImpl implements UsuarioService {
    private final UsuarioRepository usuarioRepository;
    private final ReservaRepository reservaRepository;

    @Override
    public UserDetailsService userDetailsService() {
        return (email) -> usuarioRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("usuario não encontrado"));
    }

    @Override
    public Page<Reserva> buscarReservasUsuario(UUID id, Pageable page) {
        usuarioRepository.findById(id).orElseThrow(() -> new UserNotFoundException(id));
        return reservaRepository.findAllByUsuarioId(id, page);
    }

}
