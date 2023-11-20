package co.edu.uniquindio.clinicaX.servicios.validaciones.diaLibre;

import co.edu.uniquindio.clinicaX.dto.medico.DiaLibreDTO;
import co.edu.uniquindio.clinicaX.model.DiaLibre;
import co.edu.uniquindio.clinicaX.repositorios.DiaLibreRepo;
import jakarta.validation.ValidationException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class MedicoSinDiaLibre implements ValidadorDiaLibre{
    private final DiaLibreRepo diaLibreRepo;
    @Override
    public void validar(DiaLibreDTO datos) {
        if (datos.codigoMedico() == null)
            return;
        List<DiaLibre> diasLibres = diaLibreRepo.findByMedicoCodigoAndDiaIsNotNull(datos.codigoMedico());
        System.out.println(diasLibres);

        if (!diasLibres.isEmpty()) {
            throw new ValidationException("El médico ya tiene un día libre registrado. No puede registrar más de un día libre a la vez. debe esperar a que pase su día agendado");
        }

    }
}
