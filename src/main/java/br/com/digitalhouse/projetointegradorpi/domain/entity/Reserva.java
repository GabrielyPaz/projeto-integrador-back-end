package br.com.digitalhouse.projetointegradorpi.domain.entity;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "reserva")
public class Reserva {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id")
    private UUID id;
    //    private LocalTime horaInicial;       //substituimos isso pelo localdatetime
    @JsonFormat(pattern = "dd/MM/yyyy HH:mm")
    private LocalDateTime dataInicial;
    @JsonFormat(pattern = "dd/MM/yyyy HH:mm")
    private LocalDateTime dataFinal;
    @ManyToOne
    @JoinColumn(name = "id_carro", referencedColumnName = "id", foreignKey = @ForeignKey(name = "fk_reserva_carro"))
    private Veiculo veiculo;
    @ManyToOne
    @JoinColumn(name = "id_usuario", referencedColumnName = "id", foreignKey = @ForeignKey(name = "fk_reserva_usuario"))
    private Usuario usuario;

}
