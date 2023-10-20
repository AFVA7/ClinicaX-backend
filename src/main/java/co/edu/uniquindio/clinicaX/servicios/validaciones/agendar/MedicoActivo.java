package co.edu.uniquindio.clinicaX.servicios.validaciones.agendar;

import co.edu.uniquindio.clinicaX.dto.cita.AgendarCitaDTO;
import co.edu.uniquindio.clinicaX.repositorios.MedicoRepo;
import jakarta.validation.ValidationException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class MedicoActivo implements ValidadorDeCitas {
    private final MedicoRepo medicoRepo;
    @Override
    public void validar(AgendarCitaDTO datos) {
        if (datos.idMedico()==null)
            return;
        var medicoactivo = medicoRepo.findActivoById(datos.idMedico());
        if (medicoactivo==null){
            throw new ValidationException("No se permite agendar citas con médicos " +
                    "inactivos en el sistema");
        }
    }
}
