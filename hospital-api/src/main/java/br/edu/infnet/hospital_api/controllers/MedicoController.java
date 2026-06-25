package br.edu.infnet.hospital_api.controllers;

import br.edu.infnet.hospital_api.dto.MedicoDTO;
import br.edu.infnet.hospital_api.services.MedicoService;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/medicos")
public class MedicoController {

    @Autowired
    private MedicoService service;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public MedicoDTO cadastrar(@RequestBody MedicoDTO dto) {
        return service.salvar(dto);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<MedicoDTO> listar() {
        return service.listar();
    }

    @GetMapping("/consultas-ordenadas")
    @ResponseStatus(HttpStatus.OK)
    public List<MedicoDTO> listarConsultasOrdenadas() {
        return service.listarPorQtdConsultas();
    }
}
