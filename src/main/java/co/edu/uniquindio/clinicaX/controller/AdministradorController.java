package co.edu.uniquindio.clinicaX.controller;

import co.edu.uniquindio.clinicaX.dto.admin.DetalleMedicoDTO;
import co.edu.uniquindio.clinicaX.dto.admin.ItemMedicoDto;
import co.edu.uniquindio.clinicaX.dto.admin.RegistroMedicoDTO;
import co.edu.uniquindio.clinicaX.dto.cita.ItemAtencionDTO;
import co.edu.uniquindio.clinicaX.dto.cita.ItemCitaDTO;
import co.edu.uniquindio.clinicaX.dto.pqrs.ItemPQRSDTO;
import co.edu.uniquindio.clinicaX.dto.MensajeDTO;
import co.edu.uniquindio.clinicaX.model.enums.EstadoPQRS;
import co.edu.uniquindio.clinicaX.servicios.interfaces.AdministradorServicio;
import co.edu.uniquindio.clinicaX.servicios.interfaces.MedicoServicio;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/admins")
@SecurityRequirement(name = "bearer-key")
public class AdministradorController {
    private final AdministradorServicio adminServicios;
    private final MedicoServicio medicoServicio;
    @PostMapping("/crear-medico")
    public ResponseEntity<MensajeDTO<String>> crearMedico(@Valid @RequestBody RegistroMedicoDTO medico)throws Exception{
        int codigo  = adminServicios.crearMedico(medico);
        return ResponseEntity.ok().body(new MensajeDTO<>(false, "Médico "+codigo+" registrado correctamente"));
    }
    @PutMapping("/actualizar-medico")
    public ResponseEntity<MensajeDTO<String>> acualizarMedico(@Valid @RequestBody DetalleMedicoDTO medico) throws Exception{
        adminServicios.acualizarMedico(medico);
        return ResponseEntity.ok().body(new MensajeDTO<>(false, "Medico actualizado correctamente"));
    }
    @DeleteMapping("/eliminar-medico/{codigo}")
    public ResponseEntity<MensajeDTO<String>> eliminarMedico(@PathVariable int codigo) throws Exception{
        return ResponseEntity.ok().body(new MensajeDTO<>(false, adminServicios.eliminarMedico(codigo)));
    }

    @GetMapping("/detalle-medico/{codigo}")
    public ResponseEntity<MensajeDTO<DetalleMedicoDTO>> obtenerMedico(@PathVariable int codigo) throws Exception{
        return ResponseEntity.ok().body(new MensajeDTO<>(false, adminServicios.obtenerMedico(codigo)));
    }
    @GetMapping("/listar-medicos")
    public ResponseEntity<MensajeDTO<List<ItemMedicoDto>>> listarMedicos() throws Exception{
        return ResponseEntity.ok().body(new MensajeDTO<>(false, adminServicios.listarMedicos()));
    }
    @GetMapping("/listar-pqrs")
    ResponseEntity<MensajeDTO<List<ItemPQRSDTO>>> ListarPQRS() throws Exception{
        return ResponseEntity.ok().body(new MensajeDTO<>(false, adminServicios.ListarPQRS()));
    }
    @GetMapping("/listar-citas")
    ResponseEntity<MensajeDTO<List<ItemCitaDTO>>> listarCitas() throws Exception{
        return ResponseEntity.ok().body(new MensajeDTO<>(false, adminServicios.listarCitas()));
    }


    @GetMapping("/listar-citas-realizadas/{codigo}")
    public ResponseEntity<MensajeDTO<List<ItemCitaDTO>>> listarCitasRealizadasMedico(@PathVariable int codigo) throws Exception {
        return ResponseEntity.ok().body(new MensajeDTO<>(false, medicoServicio.listarCitasRealizadasMedico(codigo)));
    }

    @PutMapping("/cambiar-estado-pqrs/{codigo}/{estado}")
    ResponseEntity<MensajeDTO<String>> cambiarEstadoPQRS(@PathVariable int codigo, @PathVariable EstadoPQRS estado) throws Exception{
        adminServicios.cambiarEstadoPQRS(codigo,estado);
        return ResponseEntity.ok().body(new MensajeDTO<>(false, "Estado de PQRS cambiada con éxito"));
    }
}
