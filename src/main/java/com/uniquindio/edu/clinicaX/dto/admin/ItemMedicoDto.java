package com.uniquindio.edu.clinicaX.dto.admin;

import com.uniquindio.edu.clinicaX.model.Especialidad;
import com.uniquindio.edu.clinicaX.model.Medico;

public record ItemMedicoDto(
        int codigo,
        String nombre,
        String cedula,
        String urlFoto,
        Especialidad Especialidad
) {
    public ItemMedicoDto(Medico medico) {
        this(
                medico.getCodigo(),
                medico.getNombre(),
                medico.getCedula(),
                medico.getUrlFoto(),
                medico.getEspecialidad()
        );
    }
}
