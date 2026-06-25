package br.edu.infnet.hospital_api.services;

import br.edu.infnet.hospital_api.dto.PacienteDTO;
import br.edu.infnet.hospital_api.models.Paciente;
import br.edu.infnet.hospital_api.repositories.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PacienteService {

    @Autowired
    private PacienteRepository repository;

    public PacienteDTO salvar(PacienteDTO dto) {
        Paciente salvo = repository.save(dto.toEntity());

        return PacienteDTO.fromEntity(salvo);
    }

    public PacienteDTO buscarPorId(Long id) {
        Paciente paciente = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("O paciente não foi encontrado"));

        return PacienteDTO.fromEntity(paciente);
    }

    public List<PacienteDTO> listar() {
        return repository.findAll()
                .stream()
                .map(PacienteDTO::fromEntity)
                .collect(Collectors.toList());
    }

    public void deletar(Long id) {
        repository.deleteById(id);
    }
}
