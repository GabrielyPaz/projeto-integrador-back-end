package br.com.digitalhouse.projetointegradorpi.domain.service;

import br.com.digitalhouse.projetointegradorpi.domain.entity.Cidade;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CidadeService {

    Cidade criarCidade(Cidade cidade);

    Page<Cidade> buscarCidades(Pageable page, String termo);

}
