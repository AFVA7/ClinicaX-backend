package co.edu.uniquindio.clinicaX.controller;

import co.edu.uniquindio.clinicaX.dto.cita.DetalleAtencionMedicaDTO;
import co.edu.uniquindio.clinicaX.dto.cita.ItemAtencionDTO;
import co.edu.uniquindio.clinicaX.dto.medico.RegistroAtencionDTO;
import co.edu.uniquindio.clinicaX.dto.security.MensajeDTO;
import co.edu.uniquindio.clinicaX.servicios.interfaces.AtencionServicio;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/atenciones")
public class AtencionController {
    private final AtencionServicio atencionServicios;

    @PutMapping("/actualizar")
    public ResponseEntity<MensajeDTO<DetalleAtencionMedicaDTO>> update(@Valid @RequestBody DetalleAtencionMedicaDTO datos) throws Exception{
        return ResponseEntity.ok().body(new MensajeDTO<>(false, atencionServicios.update(datos)
        ));
    }
    @DeleteMapping("/eliminar/{codigo}")
    public ResponseEntity<MensajeDTO<String>> eliminar(@PathVariable int codigo) throws Exception{
        atencionServicios.eliminar(codigo);
        return ResponseEntity.ok().body(new MensajeDTO<>(false, "Atención removida correctamente"));
    }
    @GetMapping("/listar/{codigo}")
    public ResponseEntity<MensajeDTO<List<ItemAtencionDTO>>> listar(@PathVariable int codigo) throws Exception {
        return ResponseEntity.ok().body(new MensajeDTO<>(false, atencionServicios.listar(codigo)));
    }
    @GetMapping("/detalle/{codigo}")
    public ResponseEntity<MensajeDTO<DetalleAtencionMedicaDTO>> verDetalle(@PathVariable int codigo) throws Exception{
        return ResponseEntity.ok().body(new MensajeDTO<>(false, atencionServicios.verDetalleRelacionadoACita(codigo)));
    }
}
