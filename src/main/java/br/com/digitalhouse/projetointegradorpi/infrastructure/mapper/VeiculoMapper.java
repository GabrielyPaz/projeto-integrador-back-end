package br.com.digitalhouse.projetointegradorpi.infrastructure.mapper;

import br.com.digitalhouse.projetointegradorpi.api.dto.response.CarroResponse;
import br.com.digitalhouse.projetointegradorpi.api.dto.response.ReservaResponse;
import br.com.digitalhouse.projetointegradorpi.api.dto.response.listResponse.CarroListResponse;
import br.com.digitalhouse.projetointegradorpi.domain.entity.Reserva;
import br.com.digitalhouse.projetointegradorpi.domain.entity.Veiculo;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import static org.mapstruct.MappingConstants.ComponentModel.SPRING;

@Mapper(componentModel = SPRING)
public interface VeiculoMapper {

    CarroListResponse veiculoParaCarroResponse(Veiculo veiculo);

}
