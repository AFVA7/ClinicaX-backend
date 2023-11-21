package co.edu.uniquindio.clinicaX.servicios.validaciones.atender;

import co.edu.uniquindio.clinicaX.dto.medico.RegistroAtencionDTO;
import co.edu.uniquindio.clinicaX.infra.errors.ValidacionDeIntegridadE;
import co.edu.uniquindio.clinicaX.model.Cita;
import co.edu.uniquindio.clinicaX.repositorios.CitaRepo;
import jakarta.validation.ValidationException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Optional;

@RequiredArgsConstructor
@Component
//valida que el médico solo pueda atender citas de hoy
public class MedicoSinAtencion implements ValidadorDeAtenciones{
    private final CitaRepo citaRepo;
    @Override
    public void validar(RegistroAtencionDTO datos) {
        Optional<Cita> optional = citaRepo.findById(datos.codigoCita());
        if(optional.isEmpty()){
            throw new ValidacionDeIntegridadE("No existe una cita con el código "+datos.codigoCita());
        }
        LocalDateTime fechaCita = optional.get().getFechaCita();
        LocalDate fechaActual = LocalDate.now();
        if(!fechaCita.toLocalDate().isEqual(fechaActual)){
            throw new ValidationException("El médico solo puede atender citas de hoy");
        }
    }
}
