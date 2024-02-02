package co.edu.uniquindio.clinicaX.servicios.validaciones.agendar;

import co.edu.uniquindio.clinicaX.dto.cita.AgendarCitaDTO;
import jakarta.validation.ValidationException;
import org.springframework.stereotype.Component;

import java.time.DayOfWeek;
import java.time.LocalDateTime;

@Component
public class FechaNoFutura implements ValidadorDeCitas{

    @Override
    public void validar(AgendarCitaDTO datos) {
        var ahora = LocalDateTime.now();
        var fechaCita = datos.fecha();
        System.out.println("Ahora " +ahora+ " fechaCita " + fechaCita);
        if (fechaCita.isBefore(ahora)) {
            throw new ValidationException("La fecha de la cita debe ser futura");
        }
    }
}
