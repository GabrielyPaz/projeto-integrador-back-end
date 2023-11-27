package br.com.digitalhouse.projetointegradorpi.domain.service.impl;

import br.com.digitalhouse.projetointegradorpi.domain.entity.*;
import br.com.digitalhouse.projetointegradorpi.domain.exceptions.CarNotFoundException;
import br.com.digitalhouse.projetointegradorpi.domain.exceptions.UserNotFoundException;
import br.com.digitalhouse.projetointegradorpi.domain.repository.CarroRepository;
import br.com.digitalhouse.projetointegradorpi.domain.repository.ReservaRepository;
import br.com.digitalhouse.projetointegradorpi.domain.repository.UsuarioRepository;
import br.com.digitalhouse.projetointegradorpi.domain.service.ReservaService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.UUID;

@Service
@AllArgsConstructor
public class ReservaServiceImpl  implements ReservaService {

    private final ReservaRepository reservaRepository;
    private final CarroRepository carroRepository;
    private final UsuarioRepository usuarioRepository;

    @Override
    public Reserva criarNovaReserva(Reserva reserva, UUID carroId, UUID usuarioId) {
        Carro carro = carroRepository.findById(carroId)
                .orElseThrow(() -> new CarNotFoundException(carroId));
        reserva.setCarro(carro);

        Usuario usuario = usuarioRepository.findById(usuarioId)
                .orElseThrow(() -> new UserNotFoundException(usuarioId));
        reserva.setUsuario(usuario);

        return this.reservaRepository.save(reserva);
    }

    @Override
    public Reserva consultarReservaPorId(UUID id) {
        return this.reservaRepository
                .findById(id)
                .orElseThrow(() -> new UserNotFoundException(id));
    }
}
