package co.edu.uniquindio.clinicaX.servicios.validaciones.agendar;

import co.edu.uniquindio.clinicaX.dto.cita.AgendarCitaDTO;
import jakarta.validation.ValidationException;
import org.springframework.stereotype.Component;

import java.time.DayOfWeek;

@Component
public class HorarioDeFuncionamientoClinica implements ValidadorDeCitas {
    @Override
    public void validar(AgendarCitaDTO datos){
        var domingo = DayOfWeek.SUNDAY.equals(datos.fecha().getDayOfWeek());
        var antesDeApertura = datos.fecha().getHour()<7;
        var despuesDeCierre = datos.fecha().getHour()>19;
        if(domingo||antesDeApertura||despuesDeCierre){
            throw new ValidationException("El horario de la clinica es de " +
                    "Lunes a Sábado de 7:00AM a 19:00 horas");
        }

    }
}
