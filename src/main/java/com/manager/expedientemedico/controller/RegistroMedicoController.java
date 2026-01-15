package com.manager.expedientemedico.controller;

import com.manager.expedientemedico.model.RegistroMedico;
import com.manager.expedientemedico.service.RegistroMedicoService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/registros-medicos")
public class RegistroMedicoController {

    private final RegistroMedicoService registroMedicoService;

    public RegistroMedicoController(RegistroMedicoService registroMedicoService) {
        this.registroMedicoService = registroMedicoService;
    }

    @PostMapping
    public RegistroMedico crear(@RequestBody RegistroMedico registro) {
        return registroMedicoService.guardar(registro);
    }

    @GetMapping("/expediente/{id}")
    public List<RegistroMedico> listarPorExpediente(@PathVariable Long id) {
        return registroMedicoService.listarPorExpediente(id);
    }

}
