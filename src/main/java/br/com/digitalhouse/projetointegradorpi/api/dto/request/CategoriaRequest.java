package br.com.digitalhouse.projetointegradorpi.api.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CategoriaRequest {

    @NotBlank
    @Schema(example = "SUV Compacto")
    private String nome;
    @NotBlank
    @Schema(example = "cinco estrelas")
    private String qualificacao;
    @NotBlank
    @Length(min = 20, max = 255)
    @Schema(example = "carro muito espaçoso")
    private String descricao;
    @Schema(example = "https://unsplash.com/pt-br/fotografias/carro-azul-com-luz-branca-e-preta-_CiyeM2kvqs")
    private String urlImagem;


}
