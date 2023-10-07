package com.uniquindio.edu.clinicaX.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;

public record RespuestaDTO(
        int codigoMensaje,
        String mensaje,
        String nombreUsuario,

        LocalDateTime fecha) {

}
