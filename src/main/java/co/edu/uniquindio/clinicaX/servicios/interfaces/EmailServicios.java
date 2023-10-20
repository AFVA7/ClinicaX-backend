package co.edu.uniquindio.clinicaX.servicios.interfaces;

import co.edu.uniquindio.clinicaX.dto.EmailDTO;

public interface EmailServicios {
    void enviarEmail(EmailDTO emailDTO) throws Exception;
}
