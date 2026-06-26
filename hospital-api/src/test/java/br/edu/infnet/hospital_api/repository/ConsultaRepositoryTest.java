package br.edu.infnet.hospital_api.repository;

import br.edu.infnet.hospital_api.models.Consulta;
import br.edu.infnet.hospital_api.models.Medico;
import br.edu.infnet.hospital_api.models.Paciente;
import br.edu.infnet.hospital_api.repositories.ConsultaRepository;
import br.edu.infnet.hospital_api.repositories.MedicoRepository;
import br.edu.infnet.hospital_api.repositories.PacienteRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@DisplayName("Teste de repositório - Consulta")
class ConsultaRepositoryTest {

    @Autowired
    private ConsultaRepository consultaRepository;

    @Autowired
    private PacienteRepository pacienteRepository;

    @Autowired
    private MedicoRepository medicoRepository;

    @Test
    @DisplayName("Deve salvar e recuperar consulta quando relacionamentos existem")
    void deveSalvarERecuperarConsulta_quandoRelacionamentosExistem_entaoPersistida() {
        Paciente p = new Paciente();
        p.setNome("Paciente Teste");
        p.setCpf("12345678900");
        p.setDataNascimento(LocalDate.of(1990, 5, 20));   // campo obrigatório
        pacienteRepository.save(p);

        Medico m = new Medico();
        m.setNome("Dr. Consulta");
        m.setCrm("CRM-CONS");
        m.setEspecialidade("Cardiologista");               // campo obrigatório
        medicoRepository.save(m);

        Consulta c = new Consulta();
        c.setPaciente(p);
        c.setMedico(m);
        c.setDataConsulta(LocalTime.from(LocalDateTime.of(2026, 6, 25, 15, 0)));
        c.setObservacoes("Rotina");
        c = consultaRepository.save(c);

        assertNotNull(c.getId());
        assertTrue(consultaRepository.findById(c.getId()).isPresent());
    }
}