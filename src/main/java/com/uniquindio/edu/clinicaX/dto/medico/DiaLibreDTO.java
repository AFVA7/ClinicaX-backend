package com.uniquindio.edu.clinicaX.dto.medico;

import java.time.LocalDate;

public record DiaLibreDTO(
        int codigoMedico,
        LocalDate fecha
) {
}
