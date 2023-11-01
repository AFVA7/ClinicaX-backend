package co.edu.uniquindio.clinicaX.servicios.validaciones.atender;

import co.edu.uniquindio.clinicaX.dto.medico.RegistroAtencionDTO;
import co.edu.uniquindio.clinicaX.repositorios.CitaRepo;
import jakarta.validation.ValidationException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@RequiredArgsConstructor
@Component
public class MedicoSinAtencion implements ValidadorDeAtenciones{
    private final CitaRepo citaRepo;
    @Override
    public void validar(RegistroAtencionDTO datos) {
        LocalDateTime fechaActual = LocalDateTime.now();
        var primerHorario = fechaActual.withHour(7);
        var ultimoHorario = fechaActual.withHour(19);

        var medicoConAtencion = citaRepo.existsByMedicoCodigoAndFechaCitaBetween(
                datos.codigoMedico(), primerHorario, ultimoHorario);
        if (!medicoConAtencion){
            throw new ValidationException("Solo puede atender citas de hoy");
        }
    }
}
