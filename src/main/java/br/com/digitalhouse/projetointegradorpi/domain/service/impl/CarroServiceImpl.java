package br.com.digitalhouse.projetointegradorpi.domain.service.impl;

import br.com.digitalhouse.projetointegradorpi.domain.entity.Carro;
import br.com.digitalhouse.projetointegradorpi.domain.exceptions.CategoryNotFoundException;
import br.com.digitalhouse.projetointegradorpi.domain.repository.CarroRepository;
import br.com.digitalhouse.projetointegradorpi.domain.service.CarroService;
import jakarta.persistence.criteria.Predicate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class CarroServiceImpl implements CarroService {

    private final CarroRepository carroRepository;

    @Autowired
    public CarroServiceImpl(CarroRepository carroRepository) {
        this.carroRepository = carroRepository;
    }

    @Override
    public Carro criarCarro(Carro carro) {
        return carroRepository.save(carro);
    }

    @Override
    public Page<Carro> buscarCarros(Pageable page, String termo) {
        return this.carroRepository.findAll((root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();
            if (termo != null && !termo.isBlank()) {
                var searchTerm = "%" + termo + "%";
                Predicate nome = criteriaBuilder.like(root.get("nome"), searchTerm);
                predicates.add(nome);
            }
            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        }, page);
    }

    @Override
    public Carro buscarCarroPorId(UUID id) {
        return this.carroRepository
                .findById(id)
                .orElseThrow(()-> new CategoryNotFoundException(id));
    }
}
