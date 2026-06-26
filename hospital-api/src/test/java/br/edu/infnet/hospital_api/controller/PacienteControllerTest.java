package br.edu.infnet.hospital_api.controller;

import br.edu.infnet.hospital_api.controllers.PacienteController;
import br.edu.infnet.hospital_api.dto.PacienteDTO;
import br.edu.infnet.hospital_api.services.PacienteService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

@ExtendWith(MockitoExtension.class)
@DisplayName("Teste de controller - Paciente")
class PacienteControllerTest {

    private MockMvc mockMvc;

    @Mock
    private PacienteService service;

    @InjectMocks
    private PacienteController controller;

    private final ObjectMapper objectMapper = new ObjectMapper();

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    }

    @Test
    @DisplayName("Deve cadastrar paciente quando dados válidos")
    void deveCadastrarPaciente_quandoDadosValidos_entaoStatus201() throws Exception {
        PacienteDTO entrada = new PacienteDTO();
        entrada.setNome("João");
        entrada.setCpf("123");

        PacienteDTO saida = new PacienteDTO();
        saida.setId(1L);
        saida.setNome("João");
        saida.setCpf("123");

        when(service.salvar(any(PacienteDTO.class))).thenReturn(saida);

        String json = objectMapper.writeValueAsString(entrada);

        MockHttpServletResponse response = mockMvc.perform(post("/pacientes")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andDo(print())
                .andReturn()
                .getResponse();

        assertThat(response.getStatus()).isEqualTo(201);
        assertThat(response.getContentAsString()).contains("João");
    }

    @Test
    @DisplayName("Deve buscar paciente quando ID existir")
    void deveBuscarPaciente_quandoIdExistir_entaoStatus200() throws Exception {
        PacienteDTO saida = new PacienteDTO();
        saida.setId(1L);
        saida.setNome("Maria");

        when(service.buscarPorId(1L)).thenReturn(saida);

        MockHttpServletResponse response = mockMvc.perform(get("/pacientes/1"))
                .andDo(print())
                .andReturn()
                .getResponse();

        assertThat(response.getStatus()).isEqualTo(200);
        assertThat(response.getContentAsString()).contains("Maria");
    }
}