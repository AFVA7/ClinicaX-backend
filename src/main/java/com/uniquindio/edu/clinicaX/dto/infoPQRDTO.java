package com.uniquindio.edu.clinicaX.dto;

import java.time.LocalDate;
import java.util.List;

//TODOS los datos de un médico que se requiere ver en el sistema
public record infoPQRDTO(
        int codigoCita,
        String motivo,
        String estado,
        String nombrePaciente,
        LocalDate fecha,
        List<String> mensajes) {
}
