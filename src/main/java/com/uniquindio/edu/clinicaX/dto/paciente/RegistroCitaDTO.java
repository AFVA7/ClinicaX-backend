package com.uniquindio.edu.clinicaX.dto.paciente;

import java.time.LocalDate;

public record RegistroCitaDTO(

        int codigoCita,
        String nombrepaciente,
        String nombremedico,
        LocalDate fecha,
        String motivo
) {
}
