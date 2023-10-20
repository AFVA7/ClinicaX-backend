package co.edu.uniquindio.clinicaX.dto.paciente;

import java.time.LocalDate;
import java.time.LocalDateTime;

public record FiltroBusquedaDTO(
        int codigoPaciente,
        LocalDateTime fechaInicio,
        LocalDateTime fechaFin
) {
}
