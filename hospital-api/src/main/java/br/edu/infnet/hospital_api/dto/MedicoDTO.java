package br.edu.infnet.hospital_api.dto;

import lombok.Data;

@Data
public class MedicoDTO {

    private Long id;
    private String nome;
    private String crm;
    private String especialidade;
}
