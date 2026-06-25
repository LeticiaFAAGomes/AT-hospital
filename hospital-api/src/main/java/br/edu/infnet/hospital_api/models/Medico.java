package br.edu.infnet.hospital_api.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "medicos")
public class Medico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "O nome é um dado obrigatório")
    private String nome;

    @NotBlank(message = "O CRM é um dado obrigatório")
    @Column(unique = true)
    private String crm;

    @NotBlank(message = "A especialidade é um dado obrigatório")
    private String especialidade;

    @OneToMany(mappedBy = "medico")
    private List<Consulta> consultas;
}
