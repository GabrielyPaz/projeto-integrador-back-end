package br.com.digitalhouse.projetointegradorpi.infrastructure.mapper;

import br.com.digitalhouse.projetointegradorpi.api.dto.response.ReservaResponse;
import br.com.digitalhouse.projetointegradorpi.domain.entity.Reserva;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import static org.mapstruct.MappingConstants.ComponentModel.SPRING;

@Mapper(componentModel = SPRING)
public interface ReservaMapper {

    @Mapping(target = "carro", source = "veiculo")
    ReservaResponse reservaParaReservaResponse(Reserva reserva);

}
