package br.com.digitalhouse.projetointegradorpi.domain.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "alugueis")
public class AluguelCarro {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id")
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "carroPraAlugar_id",
            foreignKey = @ForeignKey(name = "fk_aluguel_carro"))
    private Veiculo veiculoPraAlugar;

    @ManyToOne
    @JoinColumn(name = "cliente_id",
            foreignKey = @ForeignKey(name = "fk_cliente_carro_alugado"))
    private Cliente cliente;

    @ManyToOne
    @JoinColumn(name = "cidadeDeAluguel_id",
            foreignKey = @ForeignKey(name = "fk_cidade_carro_alugado"))
    private Cidade cidadeDeAluguel;

    private LocalDate dataAluguel;
    @Column(updatable = false)
    private LocalDateTime alugadoEm;
    private LocalDateTime entregueEm;
    // private LocalDateTime reservado
    private Boolean cancelado;
    @Column(length = 80)
    private String motivoCancelamento;

    @PrePersist
    public void noAluguel() {
        this.alugadoEm = LocalDateTime.now();
    }

    @PreUpdate
    public void naEntrega() {
        this.entregueEm = LocalDateTime.now();
    }
}
