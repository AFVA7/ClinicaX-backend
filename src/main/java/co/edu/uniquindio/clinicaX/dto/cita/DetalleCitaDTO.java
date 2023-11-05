package co.edu.uniquindio.clinicaX.dto.cita;

import co.edu.uniquindio.clinicaX.model.Cita;
import co.edu.uniquindio.clinicaX.model.enums.Especialidad;
import co.edu.uniquindio.clinicaX.model.enums.EstadoCita;

import java.time.LocalDateTime;

public record DetalleCitaDTO(
    int codigoCita,
    String nombrepaciente,
    String nombremedico,
    Especialidad especialidad,
    LocalDateTime fecha,
    String motivo,
    EstadoCita estado
) {
    public DetalleCitaDTO(Cita c) {
        this(
                c.getCodigo(),
                c.getPaciente().getNombre(),
                c.getMedico().getNombre(),
                c.getMedico().getEspecialidad(),
                c.getFechaCita(),
                c.getMotivo(),
                c.getEstado()
        );
    }
}

