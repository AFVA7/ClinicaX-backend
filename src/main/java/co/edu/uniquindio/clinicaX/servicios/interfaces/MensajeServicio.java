package co.edu.uniquindio.clinicaX.servicios.interfaces;

import co.edu.uniquindio.clinicaX.dto.RegistroMensajeDTO;
import co.edu.uniquindio.clinicaX.dto.RespuestaDTO;

import java.util.List;

public interface MensajeServicio {
    int crear(RegistroMensajeDTO mensajeDTO);
    List<RespuestaDTO> listar(int codigo);
    RespuestaDTO update(RegistroMensajeDTO datos);
    int eliminar(int codigo);
    RespuestaDTO obtener(int codigo);
}
