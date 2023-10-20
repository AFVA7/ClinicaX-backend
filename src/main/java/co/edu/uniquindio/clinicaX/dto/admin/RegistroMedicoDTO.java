package co.edu.uniquindio.clinicaX.dto.admin;

import co.edu.uniquindio.clinicaX.model.enums.Ciudad;
import co.edu.uniquindio.clinicaX.model.enums.Especialidad;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;

import java.util.List;

public record RegistroMedicoDTO(
        @NotBlank @Length(max = 200) String nombre,
        @NotBlank @Length(max = 10) String cedula,
        @NotNull Ciudad ciudad,
        @NotNull Especialidad especialidad,
        @NotBlank @Length(max = 20) String telefono,
        @NotBlank @Email @Length(max = 80) String correo,
        @NotBlank String password,
        @NotBlank String urlFoto,
        @NotEmpty List<HorarioDTO> horarios
) {
}
