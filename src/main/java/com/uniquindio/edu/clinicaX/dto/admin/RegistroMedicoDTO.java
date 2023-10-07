package com.uniquindio.edu.clinicaX.dto.admin;

import com.uniquindio.edu.clinicaX.model.Ciudad;
import com.uniquindio.edu.clinicaX.model.Especialidad;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;

import java.util.List;

public record RegistroMedicoDTO(
        @NotNull @Length(max = 200) String nombre,
        @NotNull @Length(max = 10) String cedula,
        @NotNull Ciudad ciudad,
        @NotNull Especialidad especialidad,
        @NotNull @Length(max = 20) String telefono,
        @NotNull @Email @Length(max = 80) String correo,
        @NotNull String password,
        @NotNull String urlFoto,
        @NotNull List<HorarioDTO> horarios
) {
}
