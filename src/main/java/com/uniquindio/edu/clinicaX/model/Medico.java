package com.uniquindio.edu.clinicaX.model;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.*;

import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Medico extends Usuario {
    private Especializacion especializacion;
    @OneToMany(mappedBy = "medico")
    private List<DiaLibre> diaLibres;
    @OneToMany(mappedBy = "medico")
    private List<Horario> horarios;
    @OneToMany(mappedBy = "medico")
    private List<Cita> citas;
}
