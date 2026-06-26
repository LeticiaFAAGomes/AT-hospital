package br.edu.infnet.hospital_api.service;

import br.edu.infnet.hospital_api.dto.MedicoDTO;
import br.edu.infnet.hospital_api.models.Medico;
import br.edu.infnet.hospital_api.repositories.MedicoRepository;
import br.edu.infnet.hospital_api.services.MedicoService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@DisplayName("Teste unitário - MedicoService")
class MedicoServiceTest {

    @Mock
    private MedicoRepository repository;

    @InjectMocks
    private MedicoService service;

    @Test
    @DisplayName("Deve cadastrar médico quando dados válidos")
    void deveCadastrarMedico_quandoDadosValidos_entaoRetornaMedico() {
        Medico medicoMock = new Medico();
        medicoMock.setId(1L);
        medicoMock.setNome("Dr. Teste");
        medicoMock.setCrm("CRM123");

        when(repository.save(any(Medico.class))).thenReturn(medicoMock);

        MedicoDTO dto = new MedicoDTO();
        dto.setNome("Dr. Teste");
        dto.setCrm("CRM123");

        MedicoDTO result = service.salvar(dto);
        assertNotNull(result);
        assertEquals("Dr. Teste", result.getNome());
    }
}