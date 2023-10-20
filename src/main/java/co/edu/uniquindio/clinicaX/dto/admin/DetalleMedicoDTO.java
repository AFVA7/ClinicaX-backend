package co.edu.uniquindio.clinicaX.dto.admin;

import co.edu.uniquindio.clinicaX.model.enums.Ciudad;
import co.edu.uniquindio.clinicaX.model.enums.Especialidad;
import co.edu.uniquindio.clinicaX.model.Medico;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import org.hibernate.validator.constraints.Length;

import java.util.List;

public record DetalleMedicoDTO(
        @Positive
        int codigo,
        @NotEmpty
        @Length(max = 200)
        String nombre,
        @NotEmpty
        @Length(max = 10)
        String cedula,
        @NotNull
        Ciudad ciudad,
        @NotNull
        Especialidad especialidad,
        @NotEmpty
        @Length(max = 20)
        String telefono,
        @NotEmpty
        @Email
        @Length(max = 80)
        String correo,
        @NotEmpty
        String urlFoto,
        @NotEmpty
        List<HorarioDTO> horarios

) {
    public DetalleMedicoDTO(Medico medico, List<HorarioDTO> horarios){
        this(
                medico.getCodigo(),
                medico.getNombre(),
                medico.getCedula(),
                medico.getCiudad(),
                medico.getEspecialidad(),
                medico.getTelefono(),
                medico.getCorreo(),
                medico.getUrlFoto(),
                horarios
        );
    }
}
