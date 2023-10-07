package com.uniquindio.edu.clinicaX.dto;

import com.uniquindio.edu.clinicaX.model.Especialidad;
import com.uniquindio.edu.clinicaX.model.EstadoPQRS;
import com.uniquindio.edu.clinicaX.model.Pqrs;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

//TODOS los datos de un médico que se requiere ver en el sistema
public record DetallePQRSDTO(
        int codigo,
        EstadoPQRS estado,
        String motivo,
        String nombrePaciente,
        String nombreMedico,
        Especialidad especialidad,
        LocalDateTime fecha,
        List<RespuestaDTO> mensajes) {
    public DetallePQRSDTO(Pqrs pqrs){
        this(
                pqrs.getCodigo(),
                pqrs.getEstado(),
                pqrs.getMotivo(),
                pqrs.getCita().getPaciente().getNombre(),
                pqrs.getCita().getMedico().getNombre(),
                pqrs.getCita().getMedico().getEspecialidad(),
                pqrs.getFechaCreacion(),
                new ArrayList<>()
        );
    }
}
