package br.com.digitalhouse.projetointegradorpi.domain.repository;

import br.com.digitalhouse.projetointegradorpi.domain.entity.Carro;
import br.com.digitalhouse.projetointegradorpi.domain.entity.Reserva;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ReservaRepository extends JpaSpecificationExecutor<Reserva>, JpaRepository<Reserva, UUID> {
}
