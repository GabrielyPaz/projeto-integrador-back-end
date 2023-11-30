package br.com.digitalhouse.projetointegradorpi.domain.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;
import java.util.Set;
import java.util.UUID;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "carros")
public class Veiculo {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id")
    private UUID id;
    private String modelo;
    private String descricao;

    @ManyToMany(cascade = CascadeType.PERSIST)
    @JoinTable(
            name = "caracteristicaCarro",
            joinColumns = @JoinColumn(name = "id_carro",
                    foreignKey = @ForeignKey(name = "fk_carro_categoria")),
            inverseJoinColumns = @JoinColumn(name = "id_caracteristica",
                    foreignKey = @ForeignKey(name = "fk_categoria_carro"))
    )
    private Set<Caracteristica> caracteristicasCarro;

    @Enumerated(EnumType.STRING)
    private FotoCarroEnum fotoCarro;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "categoria_id", foreignKey = @ForeignKey(name = "fk_categoria_veiculo"))
    private Categoria categoria;

    @ManyToOne(cascade = CascadeType.PERSIST) // revisao com gabriel no relacionamento
    @JoinColumn(name = "id_endereco", referencedColumnName = "id", foreignKey = @ForeignKey(name = "fk_veiculo_endereco"))
    private Cidade cidade;

    @OneToMany
    @JsonBackReference
    @JoinColumn(name = "id_carro", referencedColumnName = "id", foreignKey = @ForeignKey(name = "fk_veiculo_reservas"))
    private List<Reserva> reservas;
}