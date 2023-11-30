package br.com.digitalhouse.projetointegradorpi.domain.repository;

import br.com.digitalhouse.projetointegradorpi.domain.entity.Funcao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface FuncaoRepository extends JpaRepository<Funcao, UUID> {

    Optional<Funcao> findFuncaoByNome(String nome);
}
