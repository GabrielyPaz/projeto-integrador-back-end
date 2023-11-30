package br.com.digitalhouse.projetointegradorpi.domain.repository;

import br.com.digitalhouse.projetointegradorpi.domain.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository

public interface UsuarioRepository extends JpaRepository<Usuario, UUID> {

    Optional<Usuario> findByEmail(String email);
}
