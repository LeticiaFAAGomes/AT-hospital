package br.edu.infnet.hospital_api.services;

import br.edu.infnet.hospital_api.dto.ConsultaDTO;
import br.edu.infnet.hospital_api.models.Consulta;
import br.edu.infnet.hospital_api.models.Medico;
import br.edu.infnet.hospital_api.models.Paciente;
import br.edu.infnet.hospital_api.repositories.ConsultaRepository;
import br.edu.infnet.hospital_api.repositories.MedicoRepository;
import br.edu.infnet.hospital_api.repositories.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalTime;

@Service
public class ConsultaService {

    @Autowired
    private ConsultaRepository consultaRepository;

    @Autowired
    private PacienteRepository pacienteRepository;

    @Autowired
    private MedicoRepository medicoRepository;

    public ConsultaDTO salvar(ConsultaDTO dto) {
        Paciente paciente = pacienteRepository.findById(dto.getPacienteId())
                .orElseThrow(() -> new RuntimeException("Paciente não encontrado"));

        Medico medico = medicoRepository.findById(dto.getMedicoId())
                .orElseThrow(() -> new RuntimeException("Médico não encontrado"));

        Consulta c = new Consulta();
        c.setDataConsulta(LocalTime.from(dto.getDataConsulta()));
        c.setObservacoes(dto.getObservacoes());
        c.setPaciente(paciente);
        c.setMedico(medico);

        return ConsultaDTO.fromEntity(consultaRepository.save(c));
    }
}
