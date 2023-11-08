package br.com.digitalhouse.projetointegradorpi.api.controller;


import br.com.digitalhouse.projetointegradorpi.api.CidadeApi;
import br.com.digitalhouse.projetointegradorpi.api.dto.request.CidadeRequest;
import br.com.digitalhouse.projetointegradorpi.api.dto.response.CidadeResponse;
import br.com.digitalhouse.projetointegradorpi.api.dto.response.listResponse.CidadeListResponse;
import br.com.digitalhouse.projetointegradorpi.domain.service.CidadeService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CidadeController implements CidadeApi {

    private final ObjectMapper objectMapper;
    private final CidadeService cidadeService;

    public CidadeController(ObjectMapper objectMapper, CidadeService cidadeService) {
        this.objectMapper = objectMapper;
        this.cidadeService = cidadeService;
    }

    @Override
    public ResponseEntity<CidadeResponse> criarCidade(CidadeRequest request) {
        Cidade cidade = objectMapper.convertValue(request, Cidade.class);
        Cidade cidadeCriada = CidadeService.criarCidade(cidade);
        CidadeResponse response = objectMapper.convertValue(cidadeCriada,CidadeResponse.class);
        return ResponseEntity.ok(response);
    }

    @Override
    public ResponseEntity<Page<CidadeListResponse>> buscarCidades(Pageable page, String termo) {
        Page<Cidade> cidades =  cidadeService.buscarCidades(page, termo);
        Page<CidadeListResponse> map = cidades.map(cidade -> new CidadeListResponse(cidade.getId(),cidade.getNome(), cidade.getEstado()));
        return ResponseEntity.ok(map);
    }
}
