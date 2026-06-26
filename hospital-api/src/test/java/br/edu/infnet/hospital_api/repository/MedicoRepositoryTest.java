package br.edu.infnet.hospital_api.repository;

import br.edu.infnet.hospital_api.models.Medico;
import br.edu.infnet.hospital_api.repositories.MedicoRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;

import static org.junit.jupiter.api.Assertions.assertTrue;

@DataJpaTest
@DisplayName("Teste de repositório - Médico")
class MedicoRepositoryTest {

    @Autowired
    private MedicoRepository repository;

    @Test
    @DisplayName("Deve salvar e recuperar médico quando dados válidos")
    void deveSalvarERecuperarMedico_quandoDadosValidos_entaoPersistido() {
        Medico medico = new Medico();
        medico.setNome("Dr. Repo");
        medico.setCrm("CRM-REPO");
        medico.setEspecialidade("Cardiologia");
        medico = repository.save(medico);

        assertTrue(repository.findById(medico.getId()).isPresent());
    }
}