package br.edu.infnet.hospital_api.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Medico {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "O nome é um dado obrigatório")
    private String nome;

    @NotBlank(message = "O CRM é um dado obrigatório")
    private String crm;

    @NotBlank(message = "A especialidade é um dado obrigatório")
    private String especialidade;
}
