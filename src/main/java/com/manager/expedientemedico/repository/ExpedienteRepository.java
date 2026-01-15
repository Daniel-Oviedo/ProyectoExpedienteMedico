package com.manager.expedientemedico.repository;

import com.manager.expedientemedico.model.Expediente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExpedienteRepository extends JpaRepository<Expediente, Long> {
}
