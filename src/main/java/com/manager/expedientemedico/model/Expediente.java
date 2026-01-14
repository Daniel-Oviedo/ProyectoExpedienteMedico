package com.manager.expedientemedico.model;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;


@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "expedientes")
public class Expediente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(columnDefinition = "TEXT")
    private String observaciones;

    @Column(columnDefinition = "TEXT")
    private String medicamentos;

    private LocalDateTime fechaCreacion = LocalDateTime.now();

    @OneToOne
    @JoinColumn(name = "paciente_id", nullable = false)
    private Paciente paciente;


}
