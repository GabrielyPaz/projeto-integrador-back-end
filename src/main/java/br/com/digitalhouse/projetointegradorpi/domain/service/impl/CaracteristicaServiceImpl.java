package br.com.digitalhouse.projetointegradorpi.domain.service.impl;

import br.com.digitalhouse.projetointegradorpi.domain.entity.Caracteristica;
import br.com.digitalhouse.projetointegradorpi.domain.repository.CaracteristicaRespository;
import br.com.digitalhouse.projetointegradorpi.domain.service.CaracteristicaService;
import jakarta.persistence.criteria.Predicate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CaracteristicaServiceImpl implements CaracteristicaService {

    private final CaracteristicaRespository caracteristicaRespository;

    @Autowired
    public CaracteristicaServiceImpl(CaracteristicaRespository caracteristicaRespository) {
        this.caracteristicaRespository = caracteristicaRespository;
    }

    @Override
    public Caracteristica criarCaracteristica(Caracteristica caracteristica) {
        return caracteristicaRespository.save(caracteristica);
    }

    @Override
    public Page<Caracteristica> buscarCaracteristica(Pageable page, String termo) {
        return this.caracteristicaRespository.findAll((root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();
            if (termo != null && !termo.isBlank()) {
                var searchTerm = "%" + termo + "%";
                Predicate nome = criteriaBuilder.like(root.get("nome"), searchTerm);
                predicates.add(nome);
            }
            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        }, page);
    }
}
