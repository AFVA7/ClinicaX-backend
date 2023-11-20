package co.edu.uniquindio.clinicaX.servicios.validaciones.agendar;

import co.edu.uniquindio.clinicaX.dto.cita.AgendarCitaDTO;
import co.edu.uniquindio.clinicaX.model.enums.EstadoCita;
import co.edu.uniquindio.clinicaX.repositorios.CitaRepo;
import jakarta.validation.ValidationException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class PacienteSinConsulta implements ValidadorDeCitas {
    private final CitaRepo citaRepo;
    @Override
    public void validar(AgendarCitaDTO datos) {
        var primerHorario = datos.fecha().withHour(7);
        var ultimoHorario = datos.fecha().withHour(19);

        var pacienteConConsulta = citaRepo.existsByPacienteCodigoAndFechaCitaBetweenAndEstadoNot(
                datos.idPaciente(), primerHorario, ultimoHorario, EstadoCita.CANCELADA);
        if (pacienteConConsulta){
            throw new ValidationException("El paciente ya tiene una consulta para es día");
        }
    }
}
