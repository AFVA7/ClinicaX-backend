package co.edu.uniquindio.clinicaX.infra.security;

import co.edu.uniquindio.clinicaX.model.Cuenta;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Date;

@Service
public class TokenService {
    @Value("${api.security.secret}")
    private String apiSecret;
    public String generarToken(Cuenta cuenta){
        try {
            Algorithm algorithm = Algorithm.HMAC256(apiSecret);
            return  JWT.create()
                    .withIssuer("clinicaX")
                    .withSubject(cuenta.getCorreo())
                    .withClaim("id",cuenta.getCodigo())
                    .withExpiresAt(generarFechaDeExpiracion())
                    .sign(algorithm);
        } catch (JWTCreationException e){
            throw new RuntimeException("Error al generar el token jwt", e);
        }
    }

    public String getSubject(String token){
        if (token== null){
            throw new RuntimeException();
        }
        DecodedJWT verifier = null;
        try {
            Algorithm algorithm = Algorithm.HMAC256(apiSecret);
                verifier = JWT.require(algorithm)
                    .withIssuer("clinicaX")
                    .build()
            .verify(token);

        } catch (JWTVerificationException e){
            System.out.println(e.toString());
        }
        if(verifier.getSubject()==null){
            throw new RuntimeException("Verifier Inválido");
        }
        return verifier.getSubject();
    }

    private Instant generarFechaDeExpiracion() {
        return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-05:00"));
    }
}
