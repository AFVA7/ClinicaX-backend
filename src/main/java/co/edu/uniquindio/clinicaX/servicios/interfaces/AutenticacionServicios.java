package co.edu.uniquindio.clinicaX.servicios.interfaces;

import co.edu.uniquindio.clinicaX.infra.security.DatosJWTtoken;
import co.edu.uniquindio.clinicaX.dto.LoginDTO;

public interface AutenticacionServicios {
    DatosJWTtoken login(LoginDTO loginDTO)throws Exception;
}
