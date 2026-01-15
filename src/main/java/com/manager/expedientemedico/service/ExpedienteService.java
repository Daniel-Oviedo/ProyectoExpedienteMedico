package com.manager.expedientemedico.service;

import com.manager.expedientemedico.dto.ExpedienteRequestDTO;
import com.manager.expedientemedico.dto.ExpedienteResponseDTO;
import com.manager.expedientemedico.model.Expediente;
import com.manager.expedientemedico.model.Paciente;
import com.manager.expedientemedico.repository.ExpedienteRepository;
import com.manager.expedientemedico.repository.PacienteRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ExpedienteService {

    private final ExpedienteRepository expedienteRepository;
    private final PacienteRepository pacienteRepository;

    public ExpedienteService(ExpedienteRepository expedienteRepository,
                             PacienteRepository pacienteRepository) {
        this.expedienteRepository = expedienteRepository;
        this.pacienteRepository = pacienteRepository;
    }

    public ExpedienteResponseDTO crear(ExpedienteRequestDTO dto) {

        Paciente paciente = pacienteRepository.findById(dto.getPacienteId())
                .orElseThrow(() -> new RuntimeException("Paciente no encontrado"));

        Expediente expediente = new Expediente();
        expediente.setPaciente(paciente);
        expediente.setEstado("ACTIVO");

        Expediente guardado = expedienteRepository.save(expediente);

        ExpedienteResponseDTO response = new ExpedienteResponseDTO();
        response.setId(guardado.getId());
        response.setFechaCreacion(guardado.getFechaCreacion());
        response.setEstado(guardado.getEstado());
        response.setPacienteId(paciente.getId());

        return response;
    }

    public ExpedienteResponseDTO buscarPorId(Long id) {

        Expediente expediente = expedienteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Expediente no encontrado"));

        ExpedienteResponseDTO dto = new ExpedienteResponseDTO();
        dto.setId(expediente.getId());
        dto.setFechaCreacion(expediente.getFechaCreacion());
        dto.setEstado(expediente.getEstado());
        dto.setPacienteId(expediente.getPaciente().getId());

        return dto;
    }


    public List<ExpedienteResponseDTO> listar() {

        return expedienteRepository.findAll()
                .stream()
                .map(expediente -> {
                    ExpedienteResponseDTO dto = new ExpedienteResponseDTO();
                    dto.setId(expediente.getId());
                    dto.setFechaCreacion(expediente.getFechaCreacion());
                    dto.setEstado(expediente.getEstado());
                    dto.setPacienteId(expediente.getPaciente().getId());
                    return dto;
                })
                .toList();
    }
}
