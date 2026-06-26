package br.edu.infnet.hospital_api.controller;

import br.edu.infnet.hospital_api.controllers.ConsultaController;
import br.edu.infnet.hospital_api.dto.ConsultaDTO;
import br.edu.infnet.hospital_api.services.ConsultaService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
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

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

@ExtendWith(MockitoExtension.class)
@DisplayName("Teste de controller - Consulta")
class ConsultaControllerTest {

    private MockMvc mockMvc;

    @Mock
    private ConsultaService service;

    @InjectMocks
    private ConsultaController controller;

    private final ObjectMapper objectMapper = new ObjectMapper()
            .registerModule(new JavaTimeModule());

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    }

    @Test
    @DisplayName("Deve cadastrar consulta quando paciente e médico existem")
    void deveCadastrarConsulta_quandoPacienteEMedicoExistem_entaoStatus201() throws Exception {
        ConsultaDTO entrada = new ConsultaDTO();
        entrada.setPacienteId(1L);
        entrada.setMedicoId(1L);
        entrada.setDataConsulta(LocalDateTime.of(2026, 6, 25, 15, 0));
        entrada.setObservacoes("Dor de cabeça");

        ConsultaDTO saida = new ConsultaDTO();
        saida.setId(10L);
        saida.setPacienteId(1L);
        saida.setMedicoId(1L);
        saida.setDataConsulta(LocalDateTime.of(2026, 6, 25, 15, 0));
        saida.setObservacoes("Dor de cabeça");

        when(service.salvar(any(ConsultaDTO.class))).thenReturn(saida);

        String json = objectMapper.writeValueAsString(entrada);

        MockHttpServletResponse response = mockMvc.perform(post("/consultas")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andDo(print())
                .andReturn()
                .getResponse();

        assertThat(response.getStatus()).isEqualTo(201);
        assertThat(response.getContentAsString()).contains("Dor de cabeça");
        assertThat(response.getContentAsString()).contains("\"pacienteId\":1");
        assertThat(response.getContentAsString()).contains("\"medicoId\":1");
    }
}