package co.edu.uniquindio.clinicaX.controller;

import co.edu.uniquindio.clinicaX.dto.cita.*;
import co.edu.uniquindio.clinicaX.dto.paciente.FiltroBusquedaDTO;
import co.edu.uniquindio.clinicaX.dto.MensajeDTO;
import co.edu.uniquindio.clinicaX.servicios.interfaces.CitaServicio;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/citas")
public class CitaController {
    private final CitaServicio citaServicios;

    @PostMapping("/cancelar")
    public ResponseEntity<MensajeDTO<String>> cancelarCita(@Valid @RequestBody CancelamientoCitaDTO datos) throws Exception{
        citaServicios.cancelarCita(datos);
        return ResponseEntity.ok().body(new MensajeDTO<>(false, "Cita cancelada correctamente"));
    }
    @GetMapping("/listar-todas/{codigo}")
    public ResponseEntity<MensajeDTO<List<ItemCitaDTO>>>  listarTodasLasCitasDeUnPaciente(@PathVariable int codigo) {
        return ResponseEntity.ok().body(new MensajeDTO<>(false, citaServicios.listarTodasLasCitasDeUnPaciente(codigo)));
    }
    @GetMapping("/listar-citas-para-pqrs/{codigo}")
    public ResponseEntity<MensajeDTO<List<DetalleCitaDTO>>>  listarCitasParaPQRS(@PathVariable int codigo) {
        return ResponseEntity.ok().body(new MensajeDTO<>(false, citaServicios.listarCitasParaPQRS(codigo)));
    }

    @GetMapping("/listar-historial/{codigo}")
    public ResponseEntity<MensajeDTO<List<ItemCitaDTO>>>  listarHistorialPaciente(@PathVariable int codigo) throws Exception {
        return ResponseEntity.ok().body(new MensajeDTO<>(false, citaServicios.listarHistorialPaciente(codigo)));
    }

    @GetMapping("/citas-pendientes-paciente/{codigo}")
    public ResponseEntity<MensajeDTO<List<ItemCitaDTO>>> listarCitasPendientesPaciente(@PathVariable int codigo) throws Exception{
        return ResponseEntity.ok().body(new MensajeDTO<>(false, citaServicios.listarCitasPendientesPaciente(codigo)));
    }
    @GetMapping("/detalle-cita/{codigo}")
    ResponseEntity<MensajeDTO<DetalleCitaDTO>> verDetalleCita(@PathVariable int codigo) throws Exception{
        return ResponseEntity.ok().body(new MensajeDTO<>(false, citaServicios.verDetalleCita(codigo)));
    }
    @GetMapping("/filtrar-por-medico/{codigoMedico}")
    public ResponseEntity<MensajeDTO<List<ItemCitaDTO>>> filtrarCitasPorMedico(@PathVariable int codigoMedico) throws Exception{
        return ResponseEntity.ok().body(new MensajeDTO<>(false, citaServicios.filtrarCitasPorMedico(codigoMedico)));
    }

    @GetMapping("/filtrar-por-fecha")
    public ResponseEntity<MensajeDTO<List<ItemCitaDTO>>> filtrarCitasPorFecha(@RequestBody @Valid FiltroBusquedaDTO filtroBusquedaDTO) throws Exception{
        return ResponseEntity.ok().body(new MensajeDTO<>(false, citaServicios.filtrarCitasPorFecha(filtroBusquedaDTO)));
    }
    @GetMapping("/citas-pendientes-medico/{codigo}")
    public ResponseEntity<MensajeDTO<List<ItemCitaDTO>>> filtrarCitaspendientesMedico(@PathVariable int codigo) throws Exception{
        return ResponseEntity.ok().body(new MensajeDTO<>(false, citaServicios.filtrarCitaspendientesMedico(codigo)));
    }

}
