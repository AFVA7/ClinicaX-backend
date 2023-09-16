package com.uniquindio.edu.clinicaX.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Lob;
import jakarta.persistence.OneToMany;
import lombok.*;

import java.time.LocalDate;
import java.util.List;


@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Paciente extends Usuario{
    private LocalDate fecha_nacimiento;
    @Column(nullable = false)
    @Lob
    private Alergias alergias;
    @Column(nullable = false)
    private TipoSangre tipoSangre;
    @Column(nullable = false)
    private Eps eps;

    @OneToMany(mappedBy = "paciente")
    private List<Cita> citas;

}
