package co.edu.uniquindio.clinicaX.dto.cita;

import co.edu.uniquindio.clinicaX.model.Atencion;
import co.edu.uniquindio.clinicaX.model.Cita;
import co.edu.uniquindio.clinicaX.model.enums.Especialidad;
import co.edu.uniquindio.clinicaX.model.enums.EstadoCita;

import java.time.LocalDateTime;

public record DetalleAtencionMedicaDTO(
        int codigo,
        int codigoCita,
        String nombrePaciente,
        String nombreMedico,
        Especialidad especialidad,
        LocalDateTime fechaAtencion,
        String tratamiento,
        String notasMedicas,
        String diagnostico,
        EstadoCita estadoCita
) {
    public DetalleAtencionMedicaDTO(Cita cita){
        this(
                cita.getAtencion().getCodigo(),
                cita.getCodigo(),
                cita.getPaciente().getNombre(),
                cita.getMedico().getNombre(),
                cita.getMedico().getEspecialidad(),
                cita.getFechaCita(),
                cita.getAtencion().getTratamiento(),
                cita.getAtencion().getNotasMedicas(),
                cita.getAtencion().getDiagnostico(),
                cita.getEstado()
        );
    }
}
