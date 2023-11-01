package co.edu.uniquindio.clinicaX.servicios.validaciones.atender;

import co.edu.uniquindio.clinicaX.dto.medico.RegistroAtencionDTO;
import co.edu.uniquindio.clinicaX.model.enums.EstadoCita;
import co.edu.uniquindio.clinicaX.repositorios.CitaRepo;
import jakarta.validation.ValidationException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@RequiredArgsConstructor
@Component
public class CitaCancelada implements ValidadorDeAtenciones{
    private final CitaRepo citaRepo;
    @Override
    public void validar(RegistroAtencionDTO datos) {

        var citaCancelada = citaRepo.existsByCodigoAndEstado(datos.codigoCita(), EstadoCita.CANCELADA);
        if (citaCancelada){
            throw new ValidationException("Esta cita ya está cancelada");
        }
    }
}
