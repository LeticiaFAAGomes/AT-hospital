package br.edu.infnet.hospital_api.services;

import br.edu.infnet.hospital_api.dto.MedicoDTO;
import br.edu.infnet.hospital_api.models.Medico;
import br.edu.infnet.hospital_api.repositories.MedicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MedicoService {

    @Autowired
    private MedicoRepository repository;

    public MedicoDTO salvar(MedicoDTO dto) {
        Medico salvo = repository.save(dto.toEntity());

        return MedicoDTO.fromEntity(salvo);
    }

    public List<MedicoDTO> listar() {
        return repository.findAll()
                .stream()
                .map(MedicoDTO::fromEntity)
                .collect(Collectors.toList());
    }
}
