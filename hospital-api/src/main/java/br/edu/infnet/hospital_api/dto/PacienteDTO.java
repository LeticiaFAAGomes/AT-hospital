package br.edu.infnet.hospital_api.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class PacienteDTO {
    
    private Long id;
    private String nome;
    private String cpf;
    private LocalDate dataNascimento;
    private String telefone;
}
