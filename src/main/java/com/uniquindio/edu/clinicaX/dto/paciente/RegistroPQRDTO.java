package com.uniquindio.edu.clinicaX.dto.paciente;

public record RegistroPQRDTO(
        int codigoCita,
        String motivo,
        int codigoPaciente,
        String tipoPQR
) {
}
