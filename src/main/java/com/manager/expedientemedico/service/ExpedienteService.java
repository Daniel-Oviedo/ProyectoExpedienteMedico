package com.manager.expedientemedico.service;

import com.manager.expedientemedico.model.Expediente;
import com.manager.expedientemedico.repository.ExpedienteRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ExpedienteService {

    private final ExpedienteRepository expedienteRepository;

    public ExpedienteService(ExpedienteRepository expedienteRepository) {
        this.expedienteRepository = expedienteRepository;
    }

    public Expediente guardar(Expediente expediente) {
        return expedienteRepository.save(expediente);
    }

    public Optional<Expediente> buscarPorId(Long id) {
        return expedienteRepository.findById(id);
    }

    public List<Expediente> listar() {
        return expedienteRepository.findAll();
    }
}
