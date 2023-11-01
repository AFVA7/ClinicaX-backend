package co.edu.uniquindio.clinicaX.controller;

import co.edu.uniquindio.clinicaX.dto.EmailDTO;
import co.edu.uniquindio.clinicaX.dto.security.MensajeDTO;
import co.edu.uniquindio.clinicaX.servicios.interfaces.DiaLibreServicio;
import co.edu.uniquindio.clinicaX.servicios.interfaces.EmailServicio;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/enviar-email")
public class EmailController {
    private final EmailServicio emailServicio;
    @PostMapping("/enviar")
    public ResponseEntity<MensajeDTO<String>> enviarEmail(@RequestParam("file") EmailDTO emailDTO) throws Exception{
        emailServicio.enviarEmail(emailDTO);
        return ResponseEntity.ok().body(new MensajeDTO<>(false, "Email enviado con éxito" ));
    }

}
