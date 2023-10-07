package com.uniquindio.edu.clinicaX.servicios.interfaces;

import com.uniquindio.edu.clinicaX.dto.LoginDTO;

public interface AutenticacionServicios {
    void login(LoginDTO loginDTO)throws Exception;
}
