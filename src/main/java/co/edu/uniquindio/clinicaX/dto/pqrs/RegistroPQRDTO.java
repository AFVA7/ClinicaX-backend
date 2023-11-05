package co.edu.uniquindio.clinicaX.dto.pqrs;

import co.edu.uniquindio.clinicaX.model.enums.EstadoPQRS;
import co.edu.uniquindio.clinicaX.model.enums.TipoPQRS;

import java.time.LocalDateTime;

public record RegistroPQRDTO(
        int codigoCita,
        String motivo,
        int codigoPaciente,
        TipoPQRS tipoPQR
) {
}
