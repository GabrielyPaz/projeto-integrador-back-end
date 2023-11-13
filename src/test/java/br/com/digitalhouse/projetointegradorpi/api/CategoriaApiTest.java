package br.com.digitalhouse.projetointegradorpi.api;

import br.com.digitalhouse.projetointegradorpi.api.dto.request.CategoriaRequest;
import br.com.digitalhouse.projetointegradorpi.domain.entity.Categoria;
import br.com.digitalhouse.projetointegradorpi.domain.service.CarroService;
import br.com.digitalhouse.projetointegradorpi.domain.service.CategoriaService;
import br.com.digitalhouse.projetointegradorpi.domain.service.CidadeService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
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

@ActiveProfiles("test-integration")
@WebMvcTest(controllers = CategoriaApi.class)
class CategoriaApiTest {

    @Autowired
    private MockMvc mvc ;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private CategoriaService categoriaService;

    @Test
    void dadoUmaCategoria_quandoChamamosCriarCategoria_entaoRetornarCategoriaCriada() throws Exception {
        UUID id = UUID.randomUUID();
        String nome = "Grupo B" ;
        String qualificacao = "4 estrelas";
        String descricao = "Compacto com ar e direcao hidraulica";
        String urlImagem = "https://unsplash.com/pt-br/fotografias/carro-azul-com-luz-branca-e-preta-_CiyeM2kvqs";

        CategoriaRequest request = new CategoriaRequest(nome,qualificacao,descricao,urlImagem);
        String requestBody = objectMapper.writeValueAsString(request);

        Categoria categoria = new Categoria(id,nome,qualificacao,descricao);

        Mockito.when(categoriaService.criarCategoria(Mockito.any())).thenReturn(categoria);
        mvc.perform(post("/categorias")
                .content(requestBody)
                .contentType(MediaType
                        .APPLICATION_JSON_VALUE))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(header().string("Content-Type", MediaType.APPLICATION_JSON_VALUE))
                .andExpect(jsonPath("$.id", equalTo(id.toString())))
                .andExpect(jsonPath("$.nome", equalTo(nome)))
                .andExpect(jsonPath("$.qualificacao", equalTo(qualificacao)))
                .andExpect(jsonPath("$.descricao", equalTo(descricao)))
        ;

    }
    @Test
     void dadoUmaCategoria_quandoChamamosBuscarCategoria_entaoRetornarCategoriaindicada() throws Exception{
        Categoria categoria = new Categoria(UUID.randomUUID(),"grupo C","5 estrelas","economico com ar");

        Page<Categoria> pagina1 = new PageImpl<>(List.of(categoria));

        Mockito.when(categoriaService.buscarCategorias(Mockito.any(),Mockito.any())).thenReturn(pagina1);
        mvc.perform(get("/categorias")
                        .contentType(MediaType
                                .APPLICATION_JSON_VALUE))
                .andDo(print())

        ;
    }
    }

