package co.edu.uniquindio.clinicaX.controller;

import co.edu.uniquindio.clinicaX.dto.RegistroMensajeDTO;
import co.edu.uniquindio.clinicaX.dto.RegistroRespuestaDTO;
import co.edu.uniquindio.clinicaX.dto.RespuestaDTO;
import co.edu.uniquindio.clinicaX.dto.admin.DetalleMedicoDTO;
import co.edu.uniquindio.clinicaX.dto.admin.ItemMedicoDto;
import co.edu.uniquindio.clinicaX.dto.admin.RegistroMedicoDTO;
import co.edu.uniquindio.clinicaX.dto.security.MensajeDTO;
import co.edu.uniquindio.clinicaX.servicios.interfaces.DiaLibreServicio;
import co.edu.uniquindio.clinicaX.servicios.interfaces.MensajeServicio;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/mensajes")
public class MensajeController {
    private final MensajeServicio mensajeServicio;
    @PutMapping("/actualizar")
    public ResponseEntity<MensajeDTO<RespuestaDTO>> update(@Valid @RequestBody RegistroMensajeDTO datos) throws Exception{

        return ResponseEntity.ok().body(new MensajeDTO<>(false, mensajeServicio.update(datos)));
    }
    @DeleteMapping("/eliminar/{codigo}")
    public ResponseEntity<MensajeDTO<String>> eliminar(@PathVariable int codigo) throws Exception{
        mensajeServicio.eliminar(codigo);
        return ResponseEntity.ok().body(new MensajeDTO<>(false, "Mensaje eliminado"));
    }
    @GetMapping("/listar-medicos")
    public ResponseEntity<MensajeDTO<List<RespuestaDTO>>> listar() throws Exception {
        return ResponseEntity.ok().body(new MensajeDTO<>(false, mensajeServicio.listar()));
    }
    @GetMapping("/detalle-mensaje/{codigo}")
    public ResponseEntity<MensajeDTO<RespuestaDTO>> obtener(@PathVariable int codigo) throws Exception{
        return ResponseEntity.ok().body(new MensajeDTO<>(false, mensajeServicio.obtener(codigo)));
    }
}
