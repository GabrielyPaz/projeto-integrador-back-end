package br.com.digitalhouse.projetointegradorpi.api;

import br.com.digitalhouse.projetointegradorpi.api.dto.request.VeiculoRequest;
import br.com.digitalhouse.projetointegradorpi.domain.entity.*;
import br.com.digitalhouse.projetointegradorpi.domain.service.VeiculoService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;
import java.util.Set;
import java.util.UUID;

import static org.hamcrest.Matchers.equalTo;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

//@ActiveProfiles("test-integration")
//@WebMvcTest(controllers = CarroApi.class)
public class VeiculoApiTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private VeiculoService veiculoService;

//    @Test
    void dadoUmCarro_quandoChamamosCriarCarro_entaoRetornarCarroCriado() throws Exception {
        UUID id = UUID.randomUUID();
        String modelo = "HRV";
        String descricao = "modelo SUV compacto";
        Set<UUID> caracteristicaIds = Set.of(UUID.randomUUID());
        FotoCarroEnum fotoCarroEnum = FotoCarroEnum.SUV;
        UUID categoriaId = UUID.randomUUID();
        UUID cidadeId = UUID.randomUUID();

        VeiculoRequest request = new VeiculoRequest(modelo, descricao, caracteristicaIds, fotoCarroEnum, categoriaId, cidadeId);

        String requestBody = objectMapper.writeValueAsString(request);

        Set<Caracteristica> caracteristicas = Set.of(new Caracteristica());
        Categoria categoria = new Categoria();
        Cidade cidade = new Cidade();
        List<Reserva> reservas = List.of(new Reserva());

        Veiculo veiculo = new Veiculo(id, modelo, descricao, caracteristicas, fotoCarroEnum, categoria, cidade,reservas);
        Mockito.when(veiculoService.criarCarro(Mockito.any(), Mockito.any(), Mockito.any(), Mockito.any())).thenReturn(veiculo);
        mvc.perform(post("/carros")
                        .content(requestBody)
                        .contentType(MediaType
                                .APPLICATION_JSON_VALUE))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(header().string("Content-Type", MediaType.APPLICATION_JSON_VALUE))
                .andExpect(jsonPath("$.id", equalTo(id.toString())))
                .andExpect(jsonPath("$.modelo", equalTo(modelo)))
                .andExpect(jsonPath("$.descricao", equalTo(descricao)))
                .andExpect(jsonPath("$.caracteristicasCarro").exists())
                .andExpect(jsonPath("$.categoria.id", equalTo(categoria.getId())))
                .andExpect(jsonPath("$.categoria.nome", equalTo(categoria.getNome())))
                .andExpect(jsonPath("$.categoria.qualificacao", equalTo(categoria.getQualificacao())))
                .andExpect(jsonPath("$.categoria.descricao", equalTo(categoria.getDescricao())))
                .andExpect(jsonPath("$.cidade.id", equalTo(cidade.getId())))
                .andExpect(jsonPath("$.cidade.nome", equalTo(cidade.getNome())))
                .andExpect(jsonPath("$.cidade.estado", equalTo(cidade.getEstado())));

    }

//    @Test
    void dadoUmCarro_quandoChamamosBuscarCarros_entaoRetornarCarrosinformados() throws Exception {
        Set<Caracteristica> caracteristicas = Set.of(new Caracteristica());
        FotoCarroEnum fotoCarroEnum = FotoCarroEnum.SUV;
        Categoria categoria = new Categoria();
        Cidade cidade = new Cidade();
        List<Reserva> reservas = List.of(new Reserva());
        Veiculo veiculo = new Veiculo(UUID.randomUUID(), "Civic", "sedan mais completo", caracteristicas, fotoCarroEnum, categoria, cidade, reservas);

        Page<Veiculo> pagina1 = new PageImpl<>(List.of(veiculo));

        Mockito.when(veiculoService.buscarCarros( Mockito.any(), Mockito.any())).thenReturn(pagina1);
        mvc.perform(get("/carros")
                        .contentType(MediaType
                                .APPLICATION_JSON_VALUE))
                .andDo(print())

        ;
    }
}
