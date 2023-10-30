package br.com.digitalhouse.projetointegradorpi.domain.repository;

import br.com.digitalhouse.projetointegradorpi.domain.entity.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface CategoriaRepository extends
        JpaSpecificationExecutor<Categoria>, JpaRepository<Categoria, UUID> {

}
