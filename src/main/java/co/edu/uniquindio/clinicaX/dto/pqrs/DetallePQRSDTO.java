package co.edu.uniquindio.clinicaX.dto.pqrs;

import co.edu.uniquindio.clinicaX.dto.RespuestaDTO;
import co.edu.uniquindio.clinicaX.model.enums.Especialidad;
import co.edu.uniquindio.clinicaX.model.enums.EstadoPQRS;
import co.edu.uniquindio.clinicaX.model.Pqrs;

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
        LocalDateTime fechaDeCreacion,
        List<RespuestaDTO> mensajes) {
    public DetallePQRSDTO(Pqrs pqrs, List<RespuestaDTO> mensajes){
        this(
                pqrs.getCodigo(),
                pqrs.getEstado(),
                pqrs.getMotivo(),
                pqrs.getCita().getPaciente().getNombre(),
                pqrs.getCita().getMedico().getNombre(),
                pqrs.getCita().getMedico().getEspecialidad(),
                pqrs.getFechaCreacion(),
                mensajes
        );
    }
}
