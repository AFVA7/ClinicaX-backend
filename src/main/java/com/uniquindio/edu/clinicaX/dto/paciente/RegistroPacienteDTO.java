package com.uniquindio.edu.clinicaX.dto.paciente;

import java.time.LocalDate;

public record RegistroPacienteDTO(
        String cedula,
        String nombre,
        String telefono,
        String urlFoto,
        int codigoCiudad,
        LocalDate fechaNacimiento,
        String alergias,
        int codigoEPS,
        int tipoSangre,
        String correo,
        String password
) {
}
