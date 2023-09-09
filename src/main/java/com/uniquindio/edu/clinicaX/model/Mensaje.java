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
    private LocalDate fechaCreacion;
    private String tipo;
    private String motivo;
    @OneToOne
    private Mensaje mensaje;
    @ManyToOne
    private Cuenta cuenta;
    @ManyToOne
    private Pqr pqr;

}
