/*
package co.edu.uniquindio.clinicaX.servicios.impl;

import co.edu.uniquindio.clinicaX.infra.security.DatosJWTtoken;
import co.edu.uniquindio.clinicaX.dto.LoginDTO;
import co.edu.uniquindio.clinicaX.infra.security.TokenService;
import co.edu.uniquindio.clinicaX.model.Cuenta;
import co.edu.uniquindio.clinicaX.servicios.interfaces.AutenticacionServicio;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
@Transactional
public class AutenticacionServicioImpl implements AutenticacionServicio {
    private final AuthenticationManager authenticationManager;
    private final TokenService tokenService;
    @Override
    public DatosJWTtoken login(LoginDTO loginDTO) throws Exception {
        Authentication Autentoken = new UsernamePasswordAuthenticationToken(
                loginDTO.user(), loginDTO.passwd()
        );
        var usuarioAutenticado = authenticationManager.authenticate(Autentoken);
        var JWTtoken = tokenService.generarToken((Cuenta) usuarioAutenticado.getPrincipal());
        return new DatosJWTtoken(JWTtoken);//El controller recibirá este token  y hará un return ResponseEntity.ok(login)
    }
}
*/
