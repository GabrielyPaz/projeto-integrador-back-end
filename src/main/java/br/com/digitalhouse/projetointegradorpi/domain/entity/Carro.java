package br.com.digitalhouse.projetointegradorpi.domain.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Set;
import java.util.UUID;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="carros")
public class Carro {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id")
    private UUID id;
    private String modelo;
    private String descricao;

    @ManyToMany
    @JoinTable(
            name="caracteristicaCarro",
            joinColumns = @JoinColumn(name = "id_carro",
                    foreignKey = @ForeignKey(name = "fk_carro_categoria")),
            inverseJoinColumns = @JoinColumn(name = "id_caracteristica",
                    foreignKey = @ForeignKey(name = "fk_categoria_carro"))
    )
    private Set<Caracteristica> caracteristicasCarro;

    @OneToMany
    @JoinColumn(name="foto_id",
            foreignKey = @ForeignKey(name="fk_carro_foto"))
    private FotoCarroEnum fotoCarro;

    @OneToMany
    @JoinColumn(name="categoria_id", foreignKey = @ForeignKey(name="fk_carro_categoria"))
    private Categoria categoria;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_endereco" , referencedColumnName = "id", foreignKey = @ForeignKey(name="fk_carro_endereco"))
    private Cidade cidade;
}