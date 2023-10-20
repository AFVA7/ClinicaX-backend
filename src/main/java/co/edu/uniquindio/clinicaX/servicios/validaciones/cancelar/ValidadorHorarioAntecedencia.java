package co.edu.uniquindio.clinicaX.servicios.validaciones.cancelar;

import co.edu.uniquindio.clinicaX.dto.cita.CancelamientoCitaDTO;
import co.edu.uniquindio.clinicaX.repositorios.CitaRepo;
import jakarta.validation.ValidationException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.LocalDateTime;

@Component("ValidadorHorarioAntecedenciaCancelamiento")
@RequiredArgsConstructor
public class ValidadorHorarioAntecedencia implements ValidadorCancelamientoCitas{
    private final CitaRepo citaRepo;
    @Override
    public void validar(CancelamientoCitaDTO datos) {
        var cita  = citaRepo.getReferenceById(datos.idCita());
        var ahora = LocalDateTime.now();
        var diferenciaEnHoras = Duration.between(ahora, cita.getFechaCita()).toHours();
        if(diferenciaEnHoras<24){
            throw new ValidationException("Una cita solo puede ser " +
                    "cancelada con antecedencia de 24 horas");
        }
    }
}
