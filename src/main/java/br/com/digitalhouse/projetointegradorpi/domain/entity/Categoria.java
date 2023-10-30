package br.com.digitalhouse.projetointegradorpi.domain.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.UUID;


@Getter
@Setter
@ToString
@Table(name="categorias")
@Entity
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
}
