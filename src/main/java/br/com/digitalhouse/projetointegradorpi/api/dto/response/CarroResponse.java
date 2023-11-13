package br.com.digitalhouse.projetointegradorpi.api.dto.response;

import br.com.digitalhouse.projetointegradorpi.domain.entity.Caracteristica;
import br.com.digitalhouse.projetointegradorpi.domain.entity.Categoria;
import br.com.digitalhouse.projetointegradorpi.domain.entity.Cidade;
import br.com.digitalhouse.projetointegradorpi.domain.entity.FotoCarroEnum;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;
import java.util.UUID;

@Getter
@Setter

public class CarroResponse {

    private UUID id;
    private String modelo;
    private String descricao;
    private Set<Caracteristica> caracteristicasCarro;
    private FotoCarroEnum fotoCarro;
    private Categoria categoria;
    private Cidade cidade;
}
