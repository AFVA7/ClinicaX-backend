package com.uniquindio.edu.clinicaX.dto;

import com.uniquindio.edu.clinicaX.model.EstadoPQRS;
import com.uniquindio.edu.clinicaX.model.Pqrs;

import java.time.LocalDateTime;

public record ItemPQRSDTO(
        int codigo,
        EstadoPQRS estado,
        //no es necesario ver el motivo en un overview
        LocalDateTime fecha,
        String nombrePaciente

) {
    public ItemPQRSDTO(Pqrs p) {
        this(
                p.getCodigo(),
                p.getEstado(),
                p.getFechaCreacion(),
                p.getCita().getPaciente().getNombre()
        );
    }
}
