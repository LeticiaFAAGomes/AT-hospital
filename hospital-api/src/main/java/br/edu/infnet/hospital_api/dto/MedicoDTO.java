package br.edu.infnet.hospital_api.dto;

import br.edu.infnet.hospital_api.models.Medico;
import lombok.Data;

@Data
public class MedicoDTO {

    private Long id;
    private String nome;
    private String crm;
    private String especialidade;

    public Medico toEntity() {
        Medico m = new Medico();
        m.setId(this.id);
        m.setNome(this.nome);
        m.setCrm(this.crm);
        m.setEspecialidade(this.especialidade);
        return m;
    }

    public static MedicoDTO fromEntity(Medico m) {
        MedicoDTO dto = new MedicoDTO();
        dto.setId(m.getId());
        dto.setNome(m.getNome());
        dto.setCrm(m.getCrm());
        dto.setEspecialidade(m.getEspecialidade());
        return dto;
    }

}
