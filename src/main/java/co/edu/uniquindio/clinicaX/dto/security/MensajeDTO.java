package co.edu.uniquindio.clinicaX.dto.security;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor

public class MensajeDTO<T> {
    private boolean error;
    private T respuesta;

}
