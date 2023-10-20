package co.edu.uniquindio.clinicaX.dto.cita;

import co.edu.uniquindio.clinicaX.model.enums.Especialidad;
import co.edu.uniquindio.clinicaX.model.enums.EstadoCita;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record AgendarCitaDTO(
        @NotNull  Integer idPaciente,
        Integer idMedico,
        LocalDateTime fechaCreacion,
        @NotNull @Future LocalDateTime fecha,
        String motivo,
        Especialidad especialidad
        ) {
}
