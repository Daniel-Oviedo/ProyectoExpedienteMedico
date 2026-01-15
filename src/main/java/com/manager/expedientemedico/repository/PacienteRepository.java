package com.manager.expedientemedico.repository;


import com.manager.expedientemedico.model.Paciente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PacienteRepository  extends JpaRepository<Paciente, Long> {
}
