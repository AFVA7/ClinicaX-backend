package com.uniquindio.edu.clinicaX.dto.admin;

import com.uniquindio.edu.clinicaX.model.*;

import java.util.ArrayList;
import java.util.List;

public record DetalleMedicoDTO(
        int codigo,
        String nombre,
        String cedula,
        Ciudad ciudad,
        Especialidad especialidad,
        String telefono,
        String correo,
        String urlFoto,
        List<HorarioDTO> horarios
) {
    public DetalleMedicoDTO(Medico medico){
        this(
                medico.getCodigo(),
                medico.getNombre(),
                medico.getCedula(),
                medico.getCiudad(),
                medico.getEspecialidad(),
                medico.getTelefono(),
                medico.getCorreo(),
                medico.getUrlFoto(),
                new ArrayList<>()
        );
    }
}
