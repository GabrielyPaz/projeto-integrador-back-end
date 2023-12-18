package br.com.digitalhouse.projetointegradorpi.domain.service.impl;

import br.com.digitalhouse.projetointegradorpi.domain.entity.*;
import br.com.digitalhouse.projetointegradorpi.domain.exceptions.*;
import br.com.digitalhouse.projetointegradorpi.domain.repository.VeiculoRepository;
import br.com.digitalhouse.projetointegradorpi.domain.repository.ReservaRepository;
import br.com.digitalhouse.projetointegradorpi.domain.repository.UsuarioRepository;
import br.com.digitalhouse.projetointegradorpi.domain.service.ReservaService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
public class ReservaServiceImpl implements ReservaService {

    private final ReservaRepository reservaRepository;
    private final VeiculoRepository veiculoRepository;
    private final UsuarioRepository usuarioRepository;

    @Override
    public Reserva criarNovaReserva(Reserva reserva, UUID carroId, UUID usuarioId) {
        boolean reservaJaExiste = reservaRepository.existsReservaByDataFinalLessThanEqualAndAndDataInicialGreaterThanEqualAndVeiculoIdIs(
                reserva.getDataFinal(),
                reserva.getDataInicial(),
                carroId);

        if (reservaJaExiste) {
            throw new ReservaAlreadyExistsException();
        }

        Veiculo veiculo = veiculoRepository.findById(carroId)
                .orElseThrow(() -> new CarNotFoundException(carroId));
        reserva.setVeiculo(veiculo);
        veiculo.setReservas(List.of(reserva));

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

    @Override
    public void deletarReserva(UUID id) {
        Reserva reserva = reservaRepository
                .findById(id)
                .orElseThrow(() -> new ReservaNotFoundException(id));
        reservaRepository.delete(reserva);
    }
}
