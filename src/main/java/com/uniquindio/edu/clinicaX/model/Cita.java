package com.uniquindio.edu.clinicaX.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Cita {
    @Id
    @EqualsAndHashCode.Include
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int codigo;
    private LocalDate fechaCreacion;
    private LocalDate fechaCita;
    private String motivo;
    @OneToMany(mappedBy = "cita")
    private List<Pqr> pqrs;
    @OneToOne(fetch = FetchType.LAZY, mappedBy = "cita")
    private Atencion atencion;
    private EstadoCita estadoCita;
    @ManyToOne(fetch = FetchType.LAZY)
    private Medico medico;
    @ManyToOne(fetch = FetchType.LAZY)
    private Paciente paciente;
}
