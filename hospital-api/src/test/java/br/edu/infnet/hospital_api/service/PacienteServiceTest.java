package br.edu.infnet.hospital_api.service;

import br.edu.infnet.hospital_api.dto.PacienteDTO;
import br.edu.infnet.hospital_api.models.Paciente;
import br.edu.infnet.hospital_api.repositories.PacienteRepository;
import br.edu.infnet.hospital_api.services.PacienteService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@DisplayName("Teste unitário - PacienteService")
class PacienteServiceTest {

    @Mock
    private PacienteRepository repository;

    @InjectMocks
    private PacienteService service;

    @Test
    @DisplayName("Deve cadastrar paciente quando dados são válidos")
    void deveCadastrarPaciente_quandoDadosValidos_entaoRetornaPaciente() {
        Paciente mock = new Paciente(); mock.setId(1L); mock.setNome("João");
        when(repository.save(any(Paciente.class))).thenReturn(mock);

        PacienteDTO dto = new PacienteDTO(); dto.setNome("João"); dto.setCpf("123");
        PacienteDTO result = service.salvar(dto);

        assertNotNull(result);
        assertEquals("João", result.getNome());
    }

    @Test
    @DisplayName("Deve buscar paciente quando ID existe")
    void deveBuscarPorId_quandoIdExistir_entaoRetornaPaciente() {
        Paciente mock = new Paciente(); mock.setId(1L);
        when(repository.findById(1L)).thenReturn(Optional.of(mock));

        PacienteDTO result = service.buscarPorId(1L);
        assertEquals(1L, result.getId());
    }

    @Test
    @DisplayName("Deve excluir paciente quando ID existe")
    void deveExcluirPaciente_quandoIdExistir_entaoDeletaRegistro() {
        doNothing().when(repository).deleteById(1L);
        service.deletar(1L);
        verify(repository, times(1)).deleteById(1L);
    }

    @Test
    @DisplayName("Deve lançar exceção quando paciente não existe")
    void deveLancarExcecao_quandoPacienteNaoExistir_entaoMensagem() {
        when(repository.findById(99L)).thenReturn(Optional.empty());
        RuntimeException ex = assertThrows(RuntimeException.class,
                () -> service.buscarPorId(99L));
        assertEquals("O paciente não foi encontrado", ex.getMessage());
    }
}