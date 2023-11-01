package co.edu.uniquindio.clinicaX.dto.security;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

public record TokenDTO(
        @NotBlank
        String token,
        @NotBlank
        String refreshToken
) {

}
