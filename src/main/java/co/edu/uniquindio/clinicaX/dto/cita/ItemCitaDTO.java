package co.edu.uniquindio.clinicaX.dto.cita;

import co.edu.uniquindio.clinicaX.model.*;
import co.edu.uniquindio.clinicaX.model.enums.Especialidad;
import co.edu.uniquindio.clinicaX.model.enums.EstadoCita;

import java.time.LocalDateTime;

public record ItemCitaDTO(
        int codigo,
        String nombrePaciente,
        String nombreMedico,
        EstadoCita estadoCita,
        LocalDateTime fecha
) {
    public ItemCitaDTO(Cita c){
        this(
                c.getCodigo(),
                c.getPaciente().getNombre(),
                c.getMedico().getNombre(),
                c.getEstado(),
                c.getFechaCita()
        );
    }
}
