package co.edu.uniquindio.clinicaX.dto.medico;

public record RegistroAtencionDTO(
        Integer codigoCita,
        int codigoMedico,
        String notasMedicas,
        String tratamiento,
        String diagnostico
) {
}
