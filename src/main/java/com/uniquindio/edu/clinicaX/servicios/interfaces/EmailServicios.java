package com.uniquindio.edu.clinicaX.servicios.interfaces;

import com.uniquindio.edu.clinicaX.dto.EmailDTO;

public interface EmailServicios {
    void enviarEmail(EmailDTO emailDTO) throws Exception;
}
