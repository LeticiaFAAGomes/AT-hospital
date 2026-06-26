package br.edu.infnet.hospital_api.service;

import br.edu.infnet.hospital_api.dto.ConsultaDTO;
import br.edu.infnet.hospital_api.models.Consulta;
import br.edu.infnet.hospital_api.models.Medico;
import br.edu.infnet.hospital_api.models.Paciente;
import br.edu.infnet.hospital_api.repositories.ConsultaRepository;
import br.edu.infnet.hospital_api.repositories.MedicoRepository;
import br.edu.infnet.hospital_api.repositories.PacienteRepository;
import br.edu.infnet.hospital_api.services.ConsultaService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@DisplayName("Teste unitário - ConsultaService")
class ConsultaServiceTest {

    @Mock
    private ConsultaRepository consultaRepository;
    @Mock
    private PacienteRepository pacienteRepository;
    @Mock
    private MedicoRepository medicoRepository;

    @InjectMocks
    private ConsultaService service;

    @Test
    @DisplayName("Deve cadastrar consulta quando paciente e médico existem")
    void deveCadastrarConsulta_quandoPacienteEMedicoExistem_entaoRetornaConsulta() {
        Paciente paciente = new Paciente(); paciente.setId(1L);
        Medico medico = new Medico(); medico.setId(1L);
        when(pacienteRepository.findById(1L)).thenReturn(Optional.of(paciente));
        when(medicoRepository.findById(1L)).thenReturn(Optional.of(medico));

        Consulta consultaMock = new Consulta();
        consultaMock.setId(10L);
        consultaMock.setPaciente(paciente);
        consultaMock.setMedico(medico);
        when(consultaRepository.save(any(Consulta.class))).thenReturn(consultaMock);

        ConsultaDTO dto = new ConsultaDTO();
        dto.setPacienteId(1L);
        dto.setMedicoId(1L);
        dto.setDataConsulta(LocalDateTime.now());

        ConsultaDTO result = service.salvar(dto);
        assertNotNull(result);
        assertEquals(10L, result.getId());
    }
}