package br.edu.infnet.hospital_api.repositories;

import br.edu.infnet.hospital_api.models.Paciente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PacienteRepository extends JpaRepository<Paciente, Long> {
}
