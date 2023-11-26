package br.com.digitalhouse.projetointegradorpi.domain.entity;


import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.UUID;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="reserva")
public class Reserva {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id")
    private UUID id;
//    private LocalTime horaInicial;       //substituimos isso pelo localdatetime
    private LocalDateTime dataInicial;
    private LocalDateTime dataFinal;
    @ManyToOne
    @JoinColumn(name = "id_carro" , referencedColumnName = "id", foreignKey = @ForeignKey(name="fk_reserva_carro"))
    private Carro carro;
    @ManyToOne
    @JoinColumn(name = "id_usuario" , referencedColumnName = "id", foreignKey = @ForeignKey(name="fk_reserva_usuario"))
    private Usuario usuario;

}
