package co.edu.uniquindio.clinicaX.servicios.validaciones.atender;

import co.edu.uniquindio.clinicaX.dto.medico.RegistroAtencionDTO;
import co.edu.uniquindio.clinicaX.model.enums.EstadoCita;
import co.edu.uniquindio.clinicaX.repositorios.CitaRepo;
import jakarta.validation.ValidationException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class CitaCompletada implements ValidadorDeAtenciones{
    private final CitaRepo citaRepo;
    @Override
    public void validar(RegistroAtencionDTO datos) {

        var citaCompletada = citaRepo.existsByCodigoAndEstado(datos.codigoCita(), EstadoCita.COMPLETADA);
        if (citaCompletada){
            throw new ValidationException("Esta cita ya se atendió");
        }
    }
}
