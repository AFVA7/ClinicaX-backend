package co.edu.uniquindio.clinicaX.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record LoginDTO(
        @NotBlank
        @Email
        String email,
        @NotBlank
        String passwd
) {
}
