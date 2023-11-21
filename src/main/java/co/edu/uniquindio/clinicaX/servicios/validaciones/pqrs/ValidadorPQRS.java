package co.edu.uniquindio.clinicaX.servicios.validaciones.pqrs;

import co.edu.uniquindio.clinicaX.dto.pqrs.RegistroPQRDTO;

public interface ValidadorPQRS {
    void validar(RegistroPQRDTO datos);
}
