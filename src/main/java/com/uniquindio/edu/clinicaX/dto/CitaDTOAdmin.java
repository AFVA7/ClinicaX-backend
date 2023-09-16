package com.uniquindio.edu.clinicaX.dto;

import java.time.LocalDate;

public record CitaDTOAdmin(
        int codigoCita,
        String nombrepaciente,
        String nombremedico,
        LocalDate fecha,
        String motivo) {
}
