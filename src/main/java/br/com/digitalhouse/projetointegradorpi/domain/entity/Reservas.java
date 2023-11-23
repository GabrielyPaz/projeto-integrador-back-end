package br.com.digitalhouse.projetointegradorpi.domain.entity;


import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.UUID;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="reservas")
public class Reservas {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id")
    private UUID id;
    private LocalTime horaInicial;
    private LocalDate dataInicial;
    private LocalDate dataFinal;
    @ManyToOne
    @JoinColumn(name = "id_carro" , referencedColumnName = "id", foreignKey = @ForeignKey(name="fk_reservas_carro"))
    private Carro carro;
    @ManyToOne
    @JoinColumn(name = "id_usuario" , referencedColumnName = "id", foreignKey = @ForeignKey(name="fk_reservas_usuario"))
    private Usuario usuario;

}
