package co.edu.uniquindio.clinicaX.servicios.validaciones.pqrs;

import co.edu.uniquindio.clinicaX.dto.pqrs.RegistroPQRDTO;
import co.edu.uniquindio.clinicaX.model.enums.EstadoPQRS;
import co.edu.uniquindio.clinicaX.repositorios.AtencionRepo;
import co.edu.uniquindio.clinicaX.repositorios.CitaRepo;
import co.edu.uniquindio.clinicaX.repositorios.PQRSRepo;
import jakarta.validation.ValidationException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CitaSinAtencion implements ValidadorPQRS{
    private final AtencionRepo atencionRepo;
    @Override
    public void validar(RegistroPQRDTO datos) {
        //Valida que la cita tenga una atención
        boolean tieneAtencion = atencionRepo.existsByCitaCodigo(datos.codigoCita());
        if (!tieneAtencion){
            throw new ValidationException("La cita "+datos.codigoCita()+" aún no está atendida ");
        }
    }

}