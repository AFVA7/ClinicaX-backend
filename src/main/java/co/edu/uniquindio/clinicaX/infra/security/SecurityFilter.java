/*
package co.edu.uniquindio.clinicaX.infra.security;

import co.edu.uniquindio.clinicaX.repositorios.CuentaRepo;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class SecurityFilter extends OncePerRequestFilter {
    private final TokenService tokenService;
    private CuentaRepo cuentaRepo;
    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain)
            throws ServletException, IOException {
        var token = request.getHeader("Authorization");
        if(token!=null){
            token = token.replace("Bearer", "");
            var subject = tokenService.getSubject(token);
            if (subject != null){
                var usuario = cuentaRepo.findByCorreo(subject);
                var autenticacion = new UsernamePasswordAuthenticationToken(usuario,
                        null, usuario.getAuthorities());
                SecurityContextHolder.getContext().setAuthentication(autenticacion);
            }
        }
        filterChain.doFilter(request, response);
    }
}
*/
