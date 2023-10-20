package co.edu.uniquindio.clinicaX.servicios.validaciones.agendar;

import co.edu.uniquindio.clinicaX.dto.cita.AgendarCitaDTO;
import jakarta.validation.ValidationException;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.LocalDateTime;

@Component
public class HorarioDeAnticipación implements ValidadorDeCitas {

    @Override
    public void validar(AgendarCitaDTO datos) {
        var ahora  = LocalDateTime.now();
        var horaDeConsulta = datos.fecha();
        var diferenciaDe30Min = Duration.between(ahora, horaDeConsulta).toMinutes()<30;
        if (diferenciaDe30Min){
            throw new ValidationException("Las consultas deben programarse con al menos " +
                    "30 minitos de anticipacion");
        }
    }
}
