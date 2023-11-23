package br.com.digitalhouse.projetointegradorpi.domain.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.*;

import java.util.UUID;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="usuario")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private String nome;
    private String Sobrenome;
//    @Email
//    private Email email;
//    private Senha senha;
    @OneToOne // inserir o joincolum para relacionamento um pra um
    private Funcoes funcoes;
    @ManyToOne
    @JoinColumn(name = "id_reservas" , referencedColumnName = "id", foreignKey = @ForeignKey(name="fk_usuario_reservas"))
    private Reservas reservas;

}
