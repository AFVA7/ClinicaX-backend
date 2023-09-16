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
@Inheritance(strategy = InheritanceType.JOINED)
public class Mensaje {
    @Id
    @EqualsAndHashCode.Include
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int codigo;
    @Column(nullable = false)
    private LocalDate fechaCreacion;
    private String tipo;
    @Lob
    private String motivo;
    @OneToOne(fetch = FetchType.LAZY)
    private Mensaje mensaje;
    @ManyToOne(fetch = FetchType.LAZY)
    private Cuenta cuenta;
    @ManyToOne(fetch = FetchType.LAZY)
    private Pqr pqr;

}
