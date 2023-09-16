package com.uniquindio.edu.clinicaX.servicios;

import com.uniquindio.edu.clinicaX.dto.EmailDTO;

public interface EmailServicios {
    String enviarCorreo(EmailDTO email) throws Exception;
}
