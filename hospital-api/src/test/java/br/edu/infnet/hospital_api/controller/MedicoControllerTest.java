package br.edu.infnet.hospital_api.controller;

import br.edu.infnet.hospital_api.controllers.MedicoController;
import br.edu.infnet.hospital_api.dto.MedicoDTO;
import br.edu.infnet.hospital_api.services.MedicoService;
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

import java.util.Collections;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

@ExtendWith(MockitoExtension.class)
@DisplayName("Teste de controller - Médico")
class MedicoControllerTest {

    private MockMvc mockMvc;

    @Mock
    private MedicoService service;

    @InjectMocks
    private MedicoController controller;

    private final ObjectMapper objectMapper = new ObjectMapper();

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    }

    @Test
    @DisplayName("Deve cadastrar médico e listar quando dados válidos")
    void deveCadastrarMedicoEListar_quandoDadosValidos_entaoRetornaMedico() throws Exception {
        MedicoDTO entrada = new MedicoDTO();
        entrada.setNome("Dra. Teste");
        entrada.setCrm("CRM-123");
        entrada.setEspecialidade("Geral");

        MedicoDTO saida = new MedicoDTO();
        saida.setId(1L);
        saida.setNome("Dra. Teste");
        saida.setCrm("CRM-123");
        saida.setEspecialidade("Geral");

        when(service.salvar(any(MedicoDTO.class))).thenReturn(saida);
        when(service.listar()).thenReturn(Collections.singletonList(saida));

        String json = objectMapper.writeValueAsString(entrada);

        MockHttpServletResponse response = mockMvc.perform(post("/medicos")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andDo(print())
                .andReturn()
                .getResponse();

        assertThat(response.getStatus()).isEqualTo(201);
        assertThat(response.getContentAsString()).contains("Dra. Teste");

        response = mockMvc.perform(get("/medicos"))
                .andDo(print())
                .andReturn()
                .getResponse();

        assertThat(response.getStatus()).isEqualTo(200);
        assertThat(response.getContentAsString()).contains("Dra. Teste");
    }
}