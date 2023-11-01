package co.edu.uniquindio.clinicaX.dto.security;

public record MensajeDTO<T>(
        Boolean valor,
        T mensaje
) {
}
