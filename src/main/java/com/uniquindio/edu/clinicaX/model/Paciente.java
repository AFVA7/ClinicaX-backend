package com.uniquindio.edu.clinicaX.model;

import jakarta.persistence.Entity;
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
    private Alergias alergias;
    private TipoSangre tipoSangre;
    private Eps eps;

    @OneToMany(mappedBy = "paciente")
    private List<Cita> citas;

}
