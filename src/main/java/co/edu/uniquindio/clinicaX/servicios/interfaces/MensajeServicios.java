package co.edu.uniquindio.clinicaX.servicios.interfaces;

import co.edu.uniquindio.clinicaX.dto.RegistroRespuestaDTO;
import co.edu.uniquindio.clinicaX.dto.RespuestaDTO;

import java.util.List;

public interface MensajeServicios {
    int crear(RegistroRespuestaDTO mensajeDTO);
    List<RespuestaDTO> listar();
    RespuestaDTO update(String contenido);
    int eliminar(int codigo);
    RespuestaDTO obtener(int codigo);
}
