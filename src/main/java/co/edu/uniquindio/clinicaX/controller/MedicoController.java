package co.edu.uniquindio.clinicaX.controller;


import co.edu.uniquindio.clinicaX.dto.cita.ItemAtencionDTO;
import co.edu.uniquindio.clinicaX.dto.cita.ItemCitaDTO;
import co.edu.uniquindio.clinicaX.dto.medico.DiaLibreDTO;
import co.edu.uniquindio.clinicaX.dto.medico.RegistroAtencionDTO;
import co.edu.uniquindio.clinicaX.dto.MensajeDTO;
import co.edu.uniquindio.clinicaX.servicios.interfaces.AdministradorServicio;
import co.edu.uniquindio.clinicaX.servicios.interfaces.AtencionServicio;
import co.edu.uniquindio.clinicaX.servicios.interfaces.MedicoServicio;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/medicos")
public class MedicoController {
    private final MedicoServicio medicoServicio;
    private  final AtencionServicio atencionServicio;

    @PostMapping("/atender-cita")
    public ResponseEntity<MensajeDTO<String>> atenderCita(@Valid @RequestBody RegistroAtencionDTO datos)throws Exception{
        int codigo = medicoServicio.atenderCita(datos);
        return ResponseEntity.ok().body(new MensajeDTO<>(false, "Atención "+codigo+" realizada y registrada correctamente en el sistema"));
    }
    @PostMapping("/agendar-dia-libre")
    public ResponseEntity<MensajeDTO<String>> agendarDiaLibre(@Valid @RequestBody DiaLibreDTO diaLibreDTO)throws Exception{
        medicoServicio.agendarDiaLibre(diaLibreDTO);
        return ResponseEntity.ok().body(new MensajeDTO<>(false, "Día libre agendado"));
    }
    @GetMapping("/listar-atenciones/{codigoMedico}")
    ResponseEntity<MensajeDTO<List<ItemAtencionDTO>>> historialDeConsultas(@PathVariable int codigoMedico){
        return ResponseEntity.ok().body(new MensajeDTO<>(false, atencionServicio.listar(codigoMedico)));
    }


}
