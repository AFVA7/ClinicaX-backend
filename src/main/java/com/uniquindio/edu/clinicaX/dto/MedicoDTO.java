package com.uniquindio.edu.clinicaX.dto;

import java.util.List;

public record MedicoDTO(
        String nombre,
        String cedula,
        String telefono,
        String correo,
        String passwd,
        int codigoCiudad,
        int codigoEspecialidad,
        List<HorarioDTO> horarios
) {
}
