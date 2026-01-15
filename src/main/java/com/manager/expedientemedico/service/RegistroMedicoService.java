package com.manager.expedientemedico.service;

import com.manager.expedientemedico.dto.RegistroMedicoRequestDTO;
import com.manager.expedientemedico.dto.RegistroMedicoResponseDTO;
import com.manager.expedientemedico.model.Expediente;
import com.manager.expedientemedico.model.RegistroMedico;
import com.manager.expedientemedico.model.Usuario;
import com.manager.expedientemedico.repository.ExpedienteRepository;
import com.manager.expedientemedico.repository.RegistroMedicoRepository;
import com.manager.expedientemedico.repository.UsuarioRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RegistroMedicoService {

    private final RegistroMedicoRepository registroRepository;
    private final ExpedienteRepository expedienteRepository;
    private final UsuarioRepository usuarioRepository;

    public RegistroMedicoService(
            RegistroMedicoRepository registroRepository,
            ExpedienteRepository expedienteRepository,
            UsuarioRepository usuarioRepository
    ) {
        this.registroRepository = registroRepository;
        this.expedienteRepository = expedienteRepository;
        this.usuarioRepository = usuarioRepository;
    }

    public RegistroMedicoResponseDTO crear(RegistroMedicoRequestDTO dto) {

        Expediente expediente = expedienteRepository.findById(dto.getExpedienteId())
                .orElseThrow(() -> new RuntimeException("Expediente no encontrado"));

        Usuario usuario = usuarioRepository.findById(dto.getUsuarioId())
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        String rol = usuario.getRol().getNombre();


        if (rol.equals("PACIENTE")) {
            throw new RuntimeException("El paciente no puede crear registros médicos");
        }

        if (rol.equals("ENFERMERA")) {
            if (dto.getDiagnostico() != null || dto.getMedicamentos() != null) {
                throw new RuntimeException("La enfermera no puede asignar diagnósticos ni medicamentos");
            }
        }

        if (rol.equals("MEDICO")) {

        }

        RegistroMedico registro = new RegistroMedico();
        registro.setExpediente(expediente);
        registro.setUsuario(usuario);
        registro.setObservaciones(dto.getObservaciones());
        registro.setDiagnostico(dto.getDiagnostico());
        registro.setMedicamentos(dto.getMedicamentos());
        registro.setPresionArterial(dto.getPresionArterial());
        registro.setPeso(dto.getPeso());
        registro.setAltura(dto.getAltura());

        RegistroMedico guardado = registroRepository.save(registro);

        RegistroMedicoResponseDTO response = new RegistroMedicoResponseDTO();
        response.setId(guardado.getId());
        response.setFechaRegistro(guardado.getFechaRegistro());
        response.setObservaciones(guardado.getObservaciones());
        response.setDiagnostico(guardado.getDiagnostico());
        response.setMedicamentos(guardado.getMedicamentos());
        response.setPresionArterial(guardado.getPresionArterial());
        response.setPeso(guardado.getPeso());
        response.setAltura(guardado.getAltura());
        response.setExpedienteId(expediente.getId());
        response.setUsuarioId(usuario.getId());

        return response;
    }


    public List<RegistroMedicoResponseDTO> listarPorExpediente(Long expedienteId) {

        return registroRepository.findByExpedienteId(expedienteId)
                .stream()
                .map(registro -> {
                    RegistroMedicoResponseDTO dto = new RegistroMedicoResponseDTO();
                    dto.setId(registro.getId());
                    dto.setFechaRegistro(registro.getFechaRegistro());
                    dto.setObservaciones(registro.getObservaciones());
                    dto.setDiagnostico(registro.getDiagnostico());
                    dto.setMedicamentos(registro.getMedicamentos());
                    dto.setPresionArterial(registro.getPresionArterial());
                    dto.setPeso(registro.getPeso());
                    dto.setAltura(registro.getAltura());
                    dto.setExpedienteId(registro.getExpediente().getId());
                    dto.setUsuarioId(registro.getUsuario().getId());
                    return dto;
                })
                .toList();
    }
}
