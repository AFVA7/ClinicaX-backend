package com.uniquindio.edu.clinicaX.model;

import com.uniquindio.edu.clinicaX.dto.admin.HorarioDTO;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class HorarioMedico {
    @Id
    @EqualsAndHashCode.Include
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int codigo;
    @Column(nullable = false)
    private String dia;
    @Column(nullable = false)
    private String horaInicio;
    @Column(nullable = false)
    private String horaFin;
    @ManyToOne(fetch = FetchType.LAZY)
    private Medico medico;

}
