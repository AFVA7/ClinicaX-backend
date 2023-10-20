package co.edu.uniquindio.clinicaX.servicios.interfaces;

import co.edu.uniquindio.clinicaX.dto.security.SesionDTO;
import co.edu.uniquindio.clinicaX.dto.security.TokenDTO;

public interface SesionServicio {
    public TokenDTO login(SesionDTO sesionDTO);
}
