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
public class Pqr {
    @Id
    @EqualsAndHashCode.Include
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int codigo;
    @Column(nullable = false)
    private LocalDate fechaCreacion;
    private String tipo;
    @Lob
    private String motivo;
    @OneToMany(mappedBy = "pqr")
    private List<Mensaje> mensajes;
    @ManyToOne(fetch = FetchType.LAZY)
    private Cita cita;
    @Column(nullable = false)
    private EstadoPQRS estadoPQRS;

}
