package co.edu.uniquindio.clinicaX.dto.cita;

import co.edu.uniquindio.clinicaX.model.enums.MotivoCancelamiento;

public record CancelamientoCitaDTO(
        Integer idCita,
        MotivoCancelamiento motivo
) {
}
