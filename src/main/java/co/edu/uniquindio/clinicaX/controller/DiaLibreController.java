package co.edu.uniquindio.clinicaX.controller;

import co.edu.uniquindio.clinicaX.dto.medico.DetalleDiaLibreDTO;
import co.edu.uniquindio.clinicaX.dto.medico.DiaLibreDTO;
import co.edu.uniquindio.clinicaX.dto.MensajeDTO;
import co.edu.uniquindio.clinicaX.servicios.interfaces.DiaLibreServicio;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/dia-libre")
public class DiaLibreController {
    private final DiaLibreServicio diaLibreServicio;
    @GetMapping("/listar")
    public ResponseEntity<MensajeDTO<List<DetalleDiaLibreDTO>>> listar() throws Exception {
        return ResponseEntity.ok().body(new MensajeDTO<>(false, diaLibreServicio.listar()));
    }
    @PutMapping("/actualizar")
    public ResponseEntity<MensajeDTO<DiaLibreDTO>> update(@Valid @RequestBody DetalleDiaLibreDTO datos) throws Exception{
        return ResponseEntity.ok().body(new MensajeDTO<>(false, diaLibreServicio.update(datos)));
    }
    @DeleteMapping("/eliminar/{codigo}")
    public ResponseEntity<MensajeDTO<String>> eliminar(@PathVariable int codigo) throws Exception{
        diaLibreServicio.eliminar(codigo);
        return ResponseEntity.ok().body(new MensajeDTO<>(false, "Eliminar eliminado correctamente"));
    }
    @GetMapping("/detalle/{codigoMedico}")
    public ResponseEntity<MensajeDTO<DetalleDiaLibreDTO>> obtener(@PathVariable int codigoMedico) throws Exception{
        return ResponseEntity.ok().body(new MensajeDTO<>(false, diaLibreServicio.obtener(codigoMedico)));
    }
}
