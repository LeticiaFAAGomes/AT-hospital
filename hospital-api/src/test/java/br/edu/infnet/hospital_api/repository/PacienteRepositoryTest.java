package br.edu.infnet.hospital_api.repository;

import br.edu.infnet.hospital_api.models.Paciente;
import br.edu.infnet.hospital_api.repositories.PacienteRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertTrue;

@DataJpaTest
@DisplayName("Teste de repositório - Paciente")
class PacienteRepositoryTest {

    @Autowired
    private PacienteRepository repository;

    @Test
    @DisplayName("Deve salvar e recuperar paciente quando dados válidos")
    void deveSalvarERecuperarPaciente_quandoDadosValidos_entaoPersistido() {
        Paciente paciente = new Paciente();
        paciente.setNome("Maria Repo");
        paciente.setCpf("000");
        paciente.setDataNascimento(LocalDate.now());
        paciente = repository.save(paciente);

        assertTrue(repository.findById(paciente.getId()).isPresent());
    }
}