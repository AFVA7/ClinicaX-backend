package co.edu.uniquindio.clinicaX.controller;


import co.edu.uniquindio.clinicaX.dto.admin.ItemMedicoDto;
import co.edu.uniquindio.clinicaX.dto.admin.RegistroMedicoDTO;
import co.edu.uniquindio.clinicaX.dto.cita.ItemCitaDTO;
import co.edu.uniquindio.clinicaX.dto.medico.DiaLibreDTO;
import co.edu.uniquindio.clinicaX.dto.medico.RegistroAtencionDTO;
import co.edu.uniquindio.clinicaX.dto.security.MensajeDTO;
import co.edu.uniquindio.clinicaX.servicios.interfaces.DiaLibreServicio;
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
    @GetMapping("/listar-citas-realizadas/{codigo}")
    public ResponseEntity<MensajeDTO<List<ItemCitaDTO>>> listarCitasRealizadasMedico(@PathVariable int codigo) throws Exception {
        return ResponseEntity.ok().body(new MensajeDTO<>(false, medicoServicio.listarCitasRealizadasMedico(codigo)));
    }
}
