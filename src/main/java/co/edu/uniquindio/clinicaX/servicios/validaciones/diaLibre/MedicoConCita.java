package co.edu.uniquindio.clinicaX.servicios.validaciones.diaLibre;

import co.edu.uniquindio.clinicaX.dto.cita.AgendarCitaDTO;
import co.edu.uniquindio.clinicaX.dto.medico.DiaLibreDTO;
import co.edu.uniquindio.clinicaX.repositorios.CitaRepo;
import jakarta.validation.ValidationException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.LocalTime;

@Component
@RequiredArgsConstructor
public class MedicoConCita implements ValidadorDiaLibre{
    private final CitaRepo citaRepo;

    @Override
    public void validar(DiaLibreDTO datos) {
        if (datos.codigoMedico()==null)
            return;

        LocalDateTime inicioDiaLibre = datos.fecha().atStartOfDay();
        LocalDateTime finDiaLibre = datos.fecha().atTime(LocalTime.MAX);
        var medicoConConsulta = citaRepo.existsByMedicoCodigoAndFechaCitaBetween(
                datos.codigoMedico(), inicioDiaLibre, finDiaLibre
        );
        if (medicoConConsulta){
            throw new ValidationException("No puede agendar este día porque ya tiene al menos una consulta para ese día");
        }
    }
}


