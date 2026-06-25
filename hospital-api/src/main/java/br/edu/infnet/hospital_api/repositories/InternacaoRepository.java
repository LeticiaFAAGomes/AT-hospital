package br.edu.infnet.hospital_api.repositories;

import br.edu.infnet.hospital_api.models.Internacao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InternacaoRepository extends JpaRepository<Internacao, Long> {
}
