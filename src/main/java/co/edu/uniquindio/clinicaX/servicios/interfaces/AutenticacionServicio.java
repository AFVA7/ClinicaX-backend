package co.edu.uniquindio.clinicaX.servicios.interfaces;

import co.edu.uniquindio.clinicaX.dto.LoginDTO;
import co.edu.uniquindio.clinicaX.dto.security.TokenDTO;

public interface AutenticacionServicio {
    TokenDTO login(LoginDTO loginDTO);
}
