package co.edu.uniquindio.clinicaX.dto;

import co.edu.uniquindio.clinicaX.dto.pqrs.RegistroPQRDTO;
import co.edu.uniquindio.clinicaX.model.Pqrs;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;

import java.time.LocalDateTime;

public record RegistroMensajeDTO(
        @Positive
        int codigoCuenta,
        @Positive
        int codigoPQRS,
        @NotBlank
        String mensaje
) {
        public RegistroMensajeDTO(RegistroPQRDTO datos, Pqrs pqrs){
                this(datos.codigoPaciente(),
                        pqrs.getCodigo(),
                        datos.motivo());
        }
}
