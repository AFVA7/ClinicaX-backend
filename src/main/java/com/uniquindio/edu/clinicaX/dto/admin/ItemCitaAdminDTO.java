package com.uniquindio.edu.clinicaX.dto.admin;

import com.uniquindio.edu.clinicaX.model.Cita;
import com.uniquindio.edu.clinicaX.model.Especialidad;
import com.uniquindio.edu.clinicaX.model.EstadoCita;

import java.time.LocalDate;
import java.time.LocalDateTime;

public record ItemCitaAdminDTO(
        int codigo,
        String cedulaPaciente,
        String nombrePaciente,
        String nombreMedico,
        Especialidad especialidad,
        EstadoCita estadoCita,
        LocalDate fecha
) {
    public ItemCitaAdminDTO(Cita c){
        this(
                c.getCodigo(),
                c.getPaciente().getCedula(),
                c.getPaciente().getNombre(),
                c.getMedico().getNombre(),
                c.getMedico().getEspecialidad(),
                c.getEstado(),
                c.getFechaCita()
        );
    }
}
