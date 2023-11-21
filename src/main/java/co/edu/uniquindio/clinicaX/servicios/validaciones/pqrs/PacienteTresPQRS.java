package co.edu.uniquindio.clinicaX.servicios.validaciones.pqrs;

import co.edu.uniquindio.clinicaX.dto.pqrs.RegistroPQRDTO;
import co.edu.uniquindio.clinicaX.model.enums.EstadoPQRS;
import co.edu.uniquindio.clinicaX.repositorios.PQRSRepo;
import jakarta.validation.ValidationException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
//valida que el paciente no tenga más de 3 PQRS en proceso o nuevas
public class PacienteTresPQRS implements ValidadorPQRS{
    private final PQRSRepo pqrsRepo;
    @Override
    public void validar(RegistroPQRDTO datos) {
        //obtiene el num de PQRS activas o en proceso del paciente
        var numeroDePQRS = pqrsRepo.countByCita_PacienteCodigoOrEstadoAndEstado(datos.codigoPaciente(), EstadoPQRS.ENPROCESO, EstadoPQRS.NUEVO);
        if (numeroDePQRS>=3){
            throw new ValidationException("El paciente ya tiene 3 PQRS activas o en progreso. ");
        }
    }
}
