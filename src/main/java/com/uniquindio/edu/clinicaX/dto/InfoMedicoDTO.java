package com.uniquindio.edu.clinicaX.dto;

import com.uniquindio.edu.clinicaX.model.Ciudad;
import com.uniquindio.edu.clinicaX.model.DiaLibre;
import com.uniquindio.edu.clinicaX.model.Especializacion;
import com.uniquindio.edu.clinicaX.model.Horario;

//TODOS los datos de un médico que se requiere ver en el sistema
public record InfoMedicoDTO(
        Especializacion especializacion,
        DiaLibre diaLibre,
        Horario horario,
        Ciudad ciudad,
        String cedula,
        String nombre,
        String telefono,
        String urlFoto,
        String correo) {

}
