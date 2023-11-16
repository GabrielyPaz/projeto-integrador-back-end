package br.com.digitalhouse.projetointegradorpi.domain.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "cidades")
@AllArgsConstructor
@NoArgsConstructor
public class Cidade {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id")
    private UUID id;
    private String nome;
    private String estado;
    // PRODUTOS PARECE ESTAR ERRADO AQUI

}
