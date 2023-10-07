package com.uniquindio.edu.clinicaX.model;

import com.uniquindio.edu.clinicaX.dto.RegistroRespuestaDTO;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Optional;

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
    private LocalDateTime fecha;
    private String tipo;
    @Lob
    private String contenido;
    @OneToOne(fetch = FetchType.LAZY)
    private Mensaje mensaje;
    @ManyToOne(fetch = FetchType.LAZY)
    private Cuenta cuenta;
    @ManyToOne(fetch = FetchType.LAZY)
    private Pqrs pqrs;

}
