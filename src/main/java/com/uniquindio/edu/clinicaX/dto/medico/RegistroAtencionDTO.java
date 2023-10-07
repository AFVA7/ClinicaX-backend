package com.uniquindio.edu.clinicaX.dto.medico;

public record RegistroAtencionDTO(
        int codigoCita,
        int codigoMedico,
        String notasMedicas,
        String tratamiento,
        String diagnostico
) {
}
