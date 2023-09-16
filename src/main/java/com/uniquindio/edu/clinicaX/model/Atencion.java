package com.uniquindio.edu.clinicaX.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Atencion {
    @Id
    @EqualsAndHashCode.Include
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int codigo;
    @Lob
    private  String diagnostico;
    @Lob
    private String tratamiento;
    @Lob
    private String notasMedicas;
    @OneToOne(fetch = FetchType.LAZY)
    private Cita cita;
}
