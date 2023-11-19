package co.edu.uniquindio.clinicaX.controller;

import co.edu.uniquindio.clinicaX.dto.LoginDTO;
import co.edu.uniquindio.clinicaX.dto.paciente.RegistroPacienteDTO;
import co.edu.uniquindio.clinicaX.dto.MensajeDTO;
import co.edu.uniquindio.clinicaX.dto.security.TokenDTO;
import co.edu.uniquindio.clinicaX.servicios.interfaces.AutenticacionServicio;
import co.edu.uniquindio.clinicaX.servicios.interfaces.PacienteServicio;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
@CrossOrigin("*")
public class AutenticacionController {
    private final AutenticacionServicio autenticacionServicio;
    private final PacienteServicio pacienteServicio;
    @PostMapping("/login")
    public ResponseEntity<MensajeDTO<TokenDTO>> login(@Valid @RequestBody LoginDTO loginDTO) throws Exception {
        TokenDTO tokenDTO = autenticacionServicio.login(loginDTO);
        return ResponseEntity.ok().body(new MensajeDTO<>(false, tokenDTO));
    }

    @PostMapping("/registrarse")
    public ResponseEntity<MensajeDTO<String>> registrarse(@Valid @RequestBody RegistroPacienteDTO pacienteDTO) throws Exception{
        int codigo = pacienteServicio.registrarse(pacienteDTO);
        return ResponseEntity.ok().body(new MensajeDTO<>(false, "Paciente "+codigo+" registrado correctamente"));
    }

    @PostMapping("/refresh")
    public ResponseEntity<MensajeDTO<TokenDTO>> registrarse(@Valid @RequestBody TokenDTO tokenDTO) throws Exception{
        TokenDTO nuevoToken = autenticacionServicio.refreshToken(tokenDTO);
        return ResponseEntity.ok().body(new MensajeDTO<>(false, nuevoToken));
    }

    @GetMapping("/recuperar-passwd/{email}")
    public ResponseEntity<MensajeDTO<String>> recuperarPasswd(@Valid @PathVariable String email) throws Exception {
        pacienteServicio.recuperarPasswd(email);
        return ResponseEntity.ok().body(new MensajeDTO<>(false, "Contraseña recuperada"));
    }
}
