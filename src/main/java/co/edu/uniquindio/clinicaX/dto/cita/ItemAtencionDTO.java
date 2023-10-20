package co.edu.uniquindio.clinicaX.dto.cita;

import co.edu.uniquindio.clinicaX.model.Cita;
import co.edu.uniquindio.clinicaX.model.enums.Especialidad;

import java.time.LocalDateTime;

public record ItemAtencionDTO (
        int codigo,
        String nombrePaciente,
        String nombreMedico,
        Especialidad especialidad,
        LocalDateTime fechaAtencion
) {
    public ItemAtencionDTO(Cita cita){
        this(
                cita.getAtencion().getCodigo(),
                cita.getPaciente().getNombre(),
                cita.getMedico().getNombre(),
                cita.getMedico().getEspecialidad(),
                cita.getFechaCita()
        );
    }
}


