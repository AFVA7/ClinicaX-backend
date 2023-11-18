package co.edu.uniquindio.clinicaX.servicios.impl;

import co.edu.uniquindio.clinicaX.dto.LoginDTO;
import co.edu.uniquindio.clinicaX.dto.security.TokenDTO;
import co.edu.uniquindio.clinicaX.infra.security.model.UserDetailsImpl;
import co.edu.uniquindio.clinicaX.infra.security.servicios.JwtService;
import co.edu.uniquindio.clinicaX.infra.security.servicios.UserDetailsServiceImpl;
import co.edu.uniquindio.clinicaX.servicios.interfaces.AutenticacionServicio;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AutenticacionServicioImpl implements AutenticacionServicio {
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final UserDetailsServiceImpl userDetailsService;

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

    @Override
    public TokenDTO refreshToken(TokenDTO tokenDTO) throws Exception{

        String email = jwtService.extractUsername( tokenDTO.refreshToken() );
        UserDetails userDetails = userDetailsService.loadUserByUsername(email);

        if( jwtService.isTokenValid(tokenDTO.refreshToken(), userDetails) ){
            String tokenNuevo = jwtService.generateToken( (UserDetailsImpl) userDetails );
            return new TokenDTO( tokenNuevo, tokenDTO.refreshToken() );
        }
        throw new Exception("Error generando el token nuevo");
    }

}
