package co.edu.uniquindio.clinicaX.controller;

import co.edu.uniquindio.clinicaX.dto.RegistroRespuestaDTO;
import co.edu.uniquindio.clinicaX.dto.admin.RegistroMedicoDTO;
import co.edu.uniquindio.clinicaX.dto.cita.AgendarCitaDTO;
import co.edu.uniquindio.clinicaX.dto.cita.DetalleAtencionMedicaDTO;
import co.edu.uniquindio.clinicaX.dto.cita.DetalleCitaDTO;
import co.edu.uniquindio.clinicaX.dto.cita.ItemCitaDTO;
import co.edu.uniquindio.clinicaX.dto.paciente.DetallePacienteDTO;
import co.edu.uniquindio.clinicaX.dto.paciente.FiltroBusquedaDTO;
import co.edu.uniquindio.clinicaX.dto.paciente.ItemPacienteDTO;
import co.edu.uniquindio.clinicaX.dto.paciente.RegistroPacienteDTO;
import co.edu.uniquindio.clinicaX.dto.pqrs.DetallePQRSDTO;
import co.edu.uniquindio.clinicaX.dto.pqrs.ItemPQRSDTO;
import co.edu.uniquindio.clinicaX.dto.pqrs.RegistroPQRDTO;
import co.edu.uniquindio.clinicaX.dto.security.MensajeDTO;
import co.edu.uniquindio.clinicaX.servicios.interfaces.PacienteServicio;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/pacientes")
public class PacienteController {
    private final PacienteServicio pacienteServicio;
    @PutMapping("/editar-perfil")
    public ResponseEntity<MensajeDTO<String>> editarPerfil(@Valid @RequestBody DetallePacienteDTO pacienteDTO) throws Exception{
        pacienteServicio.editarPerfil(pacienteDTO);
        return ResponseEntity.ok().body(new MensajeDTO<>(false, "Paciente actualizado correctamente"));
    }
    @DeleteMapping("/eliminar/{codigo}")
    public ResponseEntity<MensajeDTO<String>> eliminarCuenta(@PathVariable int codigo) throws Exception{
        pacienteServicio.eliminarCuenta(codigo);
        return ResponseEntity.ok().body(new MensajeDTO<>(false, "Paciente eliminado correctamente"));
    }
    @GetMapping("/detalle/{codigo}")
    public ResponseEntity<MensajeDTO<DetallePacienteDTO>> verDetallePaciente(@PathVariable int codigo) throws Exception{
        return ResponseEntity.ok().body(new MensajeDTO<>(false, pacienteServicio.verDetallePaciente(codigo)));
    }
    @GetMapping("/listar-todos")
    public ResponseEntity<MensajeDTO<List<ItemPacienteDTO>>> listarTodos(){
        return ResponseEntity.ok().body(new MensajeDTO<>(false, pacienteServicio.listarTodos()));
    }
    @PutMapping("/activar/{codigo}")
    public ResponseEntity<MensajeDTO<String>> activarPaciente(@PathVariable int codigo){
        pacienteServicio.activarPaciente(codigo);
        return ResponseEntity.ok().body(new MensajeDTO<>(false, "Paciente activado"));
    }
    @GetMapping("/recuperar-passwd/{email}")
    public ResponseEntity<MensajeDTO<String>> recuperarPasswd(@Valid @PathVariable String email) throws Exception {
        pacienteServicio.recuperarPasswd(email);
        return ResponseEntity.ok().body(new MensajeDTO<>(false, "Contraseña recuperada"));
    }

    @PostMapping("/agendar-cita")
    public ResponseEntity<MensajeDTO<String>> agendarCita(@Valid @RequestBody AgendarCitaDTO datos)throws Exception{
        int codigo = pacienteServicio.agendarCita(datos);
        return ResponseEntity.ok().body(new MensajeDTO<>(false, "Cita "+codigo+" registrada con éxito"));
    }

    @GetMapping("/listar-pqrs/{codigo}")
    ResponseEntity<MensajeDTO<List<ItemPQRSDTO>>>  listarPQRSPaciente(@PathVariable int codigo) throws Exception{
        return ResponseEntity.ok().body(new MensajeDTO<>(false, pacienteServicio.listarPQRSPaciente(codigo)));
    }
}
