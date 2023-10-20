package co.edu.uniquindio.clinicaX.dto.pqrs;

import co.edu.uniquindio.clinicaX.model.enums.EstadoPQRS;

import java.time.LocalDateTime;

public record RegistroPQRDTO(
        int codigoCita,
        String motivo,
        int codigoPaciente,
        String tipoPQR,
        EstadoPQRS estado,
        LocalDateTime fechaCreacion
) {
}
