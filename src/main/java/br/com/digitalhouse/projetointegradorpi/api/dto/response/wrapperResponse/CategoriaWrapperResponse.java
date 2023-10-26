package br.com.digitalhouse.projetointegradorpi.api.dto.response.wrapperResponse;

import br.com.digitalhouse.projetointegradorpi.api.dto.response.listResponse.CategoriaListResponse;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class CategoriaWrapperResponse {
    private List<CategoriaListResponse> categorias;
}

