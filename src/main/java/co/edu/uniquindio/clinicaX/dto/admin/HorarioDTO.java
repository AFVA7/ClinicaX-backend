package co.edu.uniquindio.clinicaX.dto.admin;

import co.edu.uniquindio.clinicaX.model.HorarioMedico;

import java.time.LocalDate;
import java.time.LocalTime;

public record HorarioDTO(
        String dia,
        LocalTime horaInicio,
        LocalTime horaSalida

        ) {

        public HorarioDTO(HorarioMedico h){
                this(
                        h.getDia(),
                        h.getHoraInicio(),
                        h.getHoraFin()
                );
        }
}
