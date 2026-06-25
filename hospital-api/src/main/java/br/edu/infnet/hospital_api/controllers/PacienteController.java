package br.edu.infnet.hospital_api.controllers;

import br.edu.infnet.hospital_api.dto.PacienteDTO;
import br.edu.infnet.hospital_api.services.PacienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pacientes")
public class PacienteController {

    @Autowired
    private PacienteService service;

    @PostMapping
    public PacienteDTO cadastrar(@RequestBody PacienteDTO dto) {
        return service.salvar(dto);
    }

    @GetMapping("/{id}")
    public PacienteDTO buscar(@PathVariable Long id) {
        return service.buscarPorId(id);
    }

    @GetMapping
    public List<PacienteDTO> listar() {
        return service.listar();
    }

    @DeleteMapping("/{id}")
    public void deletar(@PathVariable Long id) {
        service.deletar(id);
    }
}
