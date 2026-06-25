package br.edu.infnet.hospital_api.controllers;

import br.edu.infnet.hospital_api.dto.ConsultaDTO;
import br.edu.infnet.hospital_api.services.ConsultaService;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/consultas")
public class ConsultaController {

    @Autowired
    private ConsultaService service;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ConsultaDTO cadastrar(@RequestBody ConsultaDTO dto) {
        return service.salvar(dto);
    }
}
