package br.edu.infnet.hospital_api.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ConsultaDTO {

    private Long id;
    private LocalDateTime dataConsulta;
    private String observacoes;

    private Long pacienteId;
    private Long medicoId;
}
