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
                //Expresión ternaria, si es el primer msj, no tiene un msj asociado, por tanto se establece el valor de msj asociado con un msj predeterminado
                (mensaje.getMensaje() != null) ? mensaje.getMensaje().getContenido() : "No tiene un msj asociado pq es el primer msj"
        );
    }

}
