package co.edu.uniquindio.clinicaX.controller;

import co.edu.uniquindio.clinicaX.dto.RegistroRespuestaDTO;
import co.edu.uniquindio.clinicaX.dto.pqrs.DetallePQRSDTO;
import co.edu.uniquindio.clinicaX.dto.pqrs.RegistroPQRDTO;
import co.edu.uniquindio.clinicaX.dto.MensajeDTO;
import co.edu.uniquindio.clinicaX.servicios.interfaces.PQRServicio;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/pqrs")
public class PQRSController {
    private final PQRServicio pqrServicio;
    @PostMapping("/crear-pqrs")
    ResponseEntity<MensajeDTO<String>> crearPQRS(@RequestBody @Valid RegistroPQRDTO datos) throws Exception{
        int codigo = pqrServicio.crearPQRS(datos);
        int codigoUltimoMensaje = pqrServicio.verDetallePQRS(codigo).mensajes().size();
        return ResponseEntity.ok().body(new MensajeDTO<>(false, "PQRS generada con éxito, numero de radicado: "+codigo+"\n" +
                "mensaje: "+ (codigoUltimoMensaje-1)));

    }
    @PostMapping("/responder-pqrs")
    ResponseEntity<MensajeDTO<String>> responderPQRS(@RequestBody @Valid RegistroRespuestaDTO datos) throws Exception{
        pqrServicio.responderPQRS(datos);
        return ResponseEntity.ok().body(new MensajeDTO<>(false, "Respuesta enviada"));
    }
    @GetMapping("/detalle-pqrs/{codigo}")
    ResponseEntity<MensajeDTO<DetallePQRSDTO>>  verDetellaesPQRS(@PathVariable int codigo) throws Exception{
        return ResponseEntity.ok().body(new MensajeDTO<>(false, pqrServicio.verDetallePQRS(codigo)));
    }
}
