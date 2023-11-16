package br.com.digitalhouse.projetointegradorpi.api;

import br.com.digitalhouse.projetointegradorpi.api.dto.request.CidadeRequest;
import br.com.digitalhouse.projetointegradorpi.domain.entity.Cidade;
import br.com.digitalhouse.projetointegradorpi.domain.service.CidadeService;
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
import java.util.UUID;

import static org.hamcrest.Matchers.equalTo;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@ActiveProfiles("test-integration")
@WebMvcTest(controllers = CidadeApi.class)
public class CidadeApiTest {
    @Autowired
    private MockMvc mvc;

    @Autowired
    private ObjectMapper objectMapper;
    @MockBean
    private CidadeService cidadeService;


    @Test

    void dadoUmaCidade_quandoChamamosCriarCidade_entaoRetornarCidadeCriada() throws Exception{
        UUID id = UUID.randomUUID();
        String nome = "Rio de janeiro";
        String estado = "RJ";

        CidadeRequest request = new CidadeRequest(nome,estado);
        String requestBody = objectMapper.writeValueAsString(request);

        Cidade cidade = new Cidade(id,nome,estado);

        Mockito.when(cidadeService.criarCidade(Mockito.any())).thenReturn(cidade);
        mvc.perform(post("/cidades")
                .content(requestBody)
                .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(header().string("Content-Type", MediaType.APPLICATION_JSON_VALUE))
                .andExpect(jsonPath("$.id", equalTo(id.toString())))
                .andExpect(jsonPath("$.nome", equalTo(nome)))
                .andExpect(jsonPath("$.estado", equalTo(estado)))
        ;

    }

    @Test
    void dadoUmaCidade_quandoChamamosBuscarCidades_entaoRetornarCidadeCriada() throws Exception{
        Cidade cidade = new Cidade(UUID.randomUUID(),"sao paulo","SP");
        Page<Cidade> pagina1 = new PageImpl<>(List.of(cidade));

        Mockito.when (cidadeService.buscarCidades(Mockito.any(),Mockito.any())).thenReturn(pagina1);
        mvc.perform(get("/cidades")
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
        ;

    }
}
