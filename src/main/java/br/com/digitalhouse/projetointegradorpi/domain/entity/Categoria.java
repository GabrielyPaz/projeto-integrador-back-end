package br.com.digitalhouse.projetointegradorpi.domain.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;


@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "categorias")
public class Categoria {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(length = 100)
    private String nome;

    @Column(length = 100)
    private String qualificacao;
    private String descricao;

    @Column(columnDefinition = "text")
    private String urlImagem;
////     Importei classe Enum
//    private IconeUrlEnum iconeUrl;

    // ==> CONSIDEREI NÃO TER IMAGEM

}