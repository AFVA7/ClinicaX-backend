package co.edu.uniquindio.clinicaX.servicios.impl;

import co.edu.uniquindio.clinicaX.dto.LoginDTO;
import co.edu.uniquindio.clinicaX.dto.security.TokenDTO;
import co.edu.uniquindio.clinicaX.infra.security.model.UserDetailsImpl;
import co.edu.uniquindio.clinicaX.infra.security.servicios.JwtService;
import co.edu.uniquindio.clinicaX.servicios.interfaces.AutenticacionServicio;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AutenticacionServicioImpl implements AutenticacionServicio {
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    @Override
    public TokenDTO login(LoginDTO loginDTO) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginDTO.email(),
                        loginDTO.passwd())
        );


        UserDetailsImpl user = (UserDetailsImpl) authentication.getPrincipal();


        String jwtToken = jwtService.generateToken(user);
        String refreshToken = jwtService.generateRefreshToken(user);


        return new TokenDTO(jwtToken, refreshToken);

    }
}
