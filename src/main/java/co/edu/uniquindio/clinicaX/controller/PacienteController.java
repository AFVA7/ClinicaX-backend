package co.edu.uniquindio.clinicaX.controller;

import co.edu.uniquindio.clinicaX.dto.cita.AgendarCitaDTO;
import co.edu.uniquindio.clinicaX.dto.cita.DetalleAtencionMedicaDTO;
import co.edu.uniquindio.clinicaX.dto.paciente.DetallePacienteDTO;
import co.edu.uniquindio.clinicaX.dto.paciente.ItemPacienteDTO;
import co.edu.uniquindio.clinicaX.dto.pqrs.ItemPQRSDTO;
import co.edu.uniquindio.clinicaX.dto.MensajeDTO;
import co.edu.uniquindio.clinicaX.dto.pqrs.RegistroPQRDTO;
import co.edu.uniquindio.clinicaX.servicios.interfaces.AtencionServicio;
import co.edu.uniquindio.clinicaX.servicios.interfaces.PQRServicio;
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
    private final PQRServicio pqrServicio;
    private final AtencionServicio atencionServicios;
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


    @PostMapping("/agendar-cita")
    public ResponseEntity<MensajeDTO<String>> agendarCita(@Valid @RequestBody AgendarCitaDTO datos)throws Exception{
        int codigo = pacienteServicio.agendarCita(datos);
        return ResponseEntity.ok().body(new MensajeDTO<>(false, "Cita "+codigo+" registrada con éxito"));
    }

    @GetMapping("/listar-pqrs/{codigo}")
    ResponseEntity<MensajeDTO<List<ItemPQRSDTO>>>  listarPQRSPaciente(@PathVariable int codigo) throws Exception{
        return ResponseEntity.ok().body(new MensajeDTO<>(false, pacienteServicio.listarPQRSPaciente(codigo)));
    }
    @PostMapping("/crear-pqrs")
    ResponseEntity<MensajeDTO<String>> crearPQRS(@RequestBody @Valid RegistroPQRDTO datos) throws Exception{
        int codigo = pqrServicio.crearPQRS(datos);
        int codigoUltimoMensaje = pqrServicio.verDetallePQRS(codigo).mensajes().size();
        return ResponseEntity.ok().body(new MensajeDTO<>(false, "PQRS generada con éxito, numero de radicado: "+codigo+"\n" +
                "mensaje: "+ (codigoUltimoMensaje)));
    }
}
