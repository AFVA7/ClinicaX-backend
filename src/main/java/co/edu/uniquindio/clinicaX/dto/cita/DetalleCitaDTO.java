package co.edu.uniquindio.clinicaX.dto.cita;

import co.edu.uniquindio.clinicaX.model.Cita;
import co.edu.uniquindio.clinicaX.model.enums.EstadoCita;

import java.time.LocalDateTime;

public record DetalleCitaDTO(

    int codigoCita,
    String nombrepaciente,
    String nombremedico,
    LocalDateTime fecha,
    String motivo,
    EstadoCita estado
) {
    public DetalleCitaDTO(Cita cita) {
        this(
                cita.getCodigo(),
                cita.getPaciente().getNombre(),
                cita.getMedico().getNombre(),
                cita.getFechaCita(),
                cita.getMotivo(),
                cita.getEstado()
        );
    }
}

