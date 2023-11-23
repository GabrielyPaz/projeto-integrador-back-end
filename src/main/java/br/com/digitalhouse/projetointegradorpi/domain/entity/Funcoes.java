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
@Table(name="funcoes")

public class Funcoes {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id")
    private UUID id;
    private String nome;
    @OneToOne // inserir o joincolum para relacionamento um pra um
    private Usuario usuario;

}
