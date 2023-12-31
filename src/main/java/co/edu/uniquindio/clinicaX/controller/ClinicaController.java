package co.edu.uniquindio.clinicaX.controller;

import co.edu.uniquindio.clinicaX.dto.MensajeDTO;
import co.edu.uniquindio.clinicaX.dto.admin.ItemMedicoDto;
import co.edu.uniquindio.clinicaX.model.enums.*;
import co.edu.uniquindio.clinicaX.servicios.interfaces.ClinicaServicio;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/clinica")
public class ClinicaController {

    private final ClinicaServicio clinicaServicio;

    @GetMapping("/lista-ciudades")
    public ResponseEntity<MensajeDTO<List<Ciudad>>> listarCiudades() {
        return ResponseEntity.ok().body(new MensajeDTO<>(false, clinicaServicio.listarCiudades()));
    }

    @GetMapping("/lista-especialidades")
    public ResponseEntity<MensajeDTO<List<Especialidad>>>  listarEspecialeidades() {
        return ResponseEntity.ok().body(new MensajeDTO<>(false, clinicaServicio.listarEspecialeidades()));
    }

    @GetMapping("/lista-tipo-sangre")
    public ResponseEntity<MensajeDTO<List<TipoSangre>>> listarTipoSangre() {
        return ResponseEntity.ok().body(new MensajeDTO<>(false, clinicaServicio.listarTipoSangre()));
    }

    @GetMapping("/lista-eps")
    public ResponseEntity<MensajeDTO<List<Eps>>> listarEps() {
        return ResponseEntity.ok().body(new MensajeDTO<>(false, clinicaServicio.listarEps()));
    }

    @GetMapping("/listar-medicos/{especialidad}")
    public ResponseEntity<MensajeDTO<List<ItemMedicoDto>>> obtenerMedicosPorEspecialidad(@PathVariable Especialidad especialidad) throws Exception {
        return ResponseEntity.ok().body(new MensajeDTO<>(false, clinicaServicio.obtenerMedicosPorEspecialidad(especialidad)));
    }
    @GetMapping("/listar-medicos")
    public ResponseEntity<MensajeDTO<List<ItemMedicoDto>>> listarMedicos() throws Exception {
        return ResponseEntity.ok().body(new MensajeDTO<>(false, clinicaServicio.listarMedicos()));
    }

    @GetMapping("/listar-motivos-cancelamiento")
    public ResponseEntity<MensajeDTO<List<MotivoCancelamiento>>> listarMotivosCancelamiento() {
        System.out.println("hola");
        return ResponseEntity.ok().body(new MensajeDTO<>(false, clinicaServicio.listarMotivosCancelamiento()));
    }
    @GetMapping("/lista-tipo-pqrs")
    public ResponseEntity<MensajeDTO<List<TipoPQRS>>> listarTipoPQRS() {
        return ResponseEntity.ok().body(new MensajeDTO<>(false, clinicaServicio.listarTipoPQRS()));
    }
}
