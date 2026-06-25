package br.edu.infnet.hospital_api.repositories;

import br.edu.infnet.hospital_api.models.Consulta;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ConsultaRepository extends JpaRepository<Consulta, Long> {
}
