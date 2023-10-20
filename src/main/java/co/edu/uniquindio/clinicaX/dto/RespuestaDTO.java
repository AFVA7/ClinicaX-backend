package co.edu.uniquindio.clinicaX.dto;
import co.edu.uniquindio.clinicaX.model.Mensaje;

import java.time.LocalDateTime;
import java.util.List;

public record RespuestaDTO(
        int codigoMensaje,
        String contenido,
        String nombreUsuario,
        LocalDateTime fecha,
        String mensajesAsociados) {
    public RespuestaDTO(Mensaje mensaje){
        this(
                mensaje.getCodigo(),
                mensaje.getContenido(),
                mensaje.getCuenta().getCorreo(),
                mensaje.getFecha(),
                mensaje.getPqrs().getMotivo()
        );
    }

}
