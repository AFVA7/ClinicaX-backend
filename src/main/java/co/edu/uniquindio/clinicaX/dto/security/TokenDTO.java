package co.edu.uniquindio.clinicaX.dto.security;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor

public class TokenDTO {
    @NotBlank
    private String token;
    @NotBlank
    private String refreshToken;

}
