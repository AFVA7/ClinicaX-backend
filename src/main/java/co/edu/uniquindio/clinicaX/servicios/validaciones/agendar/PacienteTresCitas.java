package co.edu.uniquindio.clinicaX.servicios.validaciones.agendar;

import co.edu.uniquindio.clinicaX.dto.cita.AgendarCitaDTO;
import co.edu.uniquindio.clinicaX.model.enums.EstadoCita;
import co.edu.uniquindio.clinicaX.repositorios.CitaRepo;
import jakarta.validation.ValidationException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PacienteTresCitas implements ValidadorDeCitas{
    private final CitaRepo citaRepo;
    @Override
    public void validar(AgendarCitaDTO datos) {
        //obtiene el num de citas programadas del paciente
        var numeroDeCitas = citaRepo.countByPacienteCodigoAndEstado(datos.idPaciente(), EstadoCita.PROGRAMADA);
        if (numeroDeCitas>=3){
            throw new ValidationException("El paciente ya tiene 3 citas agendadas. ");
        }


    }
}
