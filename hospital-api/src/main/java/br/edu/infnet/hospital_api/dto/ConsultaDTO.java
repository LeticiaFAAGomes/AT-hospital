package br.edu.infnet.hospital_api.dto;

import br.edu.infnet.hospital_api.models.Consulta;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ConsultaDTO {

    private Long id;
    private LocalDateTime dataConsulta;
    private String observacoes;

    private Long pacienteId;
    private Long medicoId;

    public static ConsultaDTO fromEntity(Consulta c) {
        ConsultaDTO dto = new ConsultaDTO();
        dto.setId(c.getId());
        dto.setDataConsulta(LocalDateTime.from(c.getDataConsulta()));
        dto.setObservacoes(c.getObservacoes());
        dto.setPacienteId(c.getPaciente().getId());
        dto.setMedicoId(c.getMedico().getId());
        return dto;
    }

}
