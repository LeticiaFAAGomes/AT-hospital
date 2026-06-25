package br.edu.infnet.hospital_api.dto;

import br.edu.infnet.hospital_api.models.Paciente;
import lombok.Data;

import java.time.LocalDate;

@Data
public class PacienteDTO {

    private Long id;
    private String nome;
    private String cpf;
    private LocalDate dataNascimento;
    private String telefone;

    public Paciente toEntity() {
        Paciente p = new Paciente();
        p.setId(this.id);
        p.setNome(this.nome);
        p.setCpf(this.cpf);
        p.setDataNascimento(this.dataNascimento);
        p.setTelefone(this.telefone);
        return p;
    }

    public static PacienteDTO fromEntity(Paciente p) {
        PacienteDTO dto = new PacienteDTO();
        dto.setId(p.getId());
        dto.setNome(p.getNome());
        dto.setCpf(p.getCpf());
        dto.setDataNascimento(p.getDataNascimento());
        dto.setTelefone(p.getTelefone());
        return dto;
    }
}
