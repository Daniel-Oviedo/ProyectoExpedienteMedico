package com.manager.expedientemedico.service;

import com.manager.expedientemedico.model.RegistroMedico;
import com.manager.expedientemedico.repository.RegistroMedicoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RegistroMedicoService {

    private final RegistroMedicoRepository registroMedicoRepository;

    public RegistroMedicoService(RegistroMedicoRepository registroMedicoRepository) {
        this.registroMedicoRepository = registroMedicoRepository;
    }

    public RegistroMedico guardar(RegistroMedico registro) {
        return registroMedicoRepository.save(registro);
    }

    public List<RegistroMedico> listarPorExpediente(Long expedienteId) {
        return registroMedicoRepository.findByExpedienteId(expedienteId);
    }
}
