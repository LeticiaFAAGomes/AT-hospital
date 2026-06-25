package br.edu.infnet.hospital_api.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Paciente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "O nome é um dado obrigatório")
    private String nome;

    @NotBlank(message = "O CPF é um dado obrigatório")
    @Column(unique = true)
    private String cpf;

    @NotBlank(message = "A data de nascimento é um dado obrigatório")
    private LocalDate dataNascimento;

    private String telefone;
}
