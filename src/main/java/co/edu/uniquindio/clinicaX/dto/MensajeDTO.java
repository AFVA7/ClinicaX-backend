package co.edu.uniquindio.clinicaX.dto;

public record MensajeDTO<T>(
        Boolean valor,
        T mensaje
) {
}
