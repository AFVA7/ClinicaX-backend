package co.edu.uniquindio.clinicaX.infra.security;

import co.edu.uniquindio.clinicaX.infra.security.config.JwtAuthenticationEntryPoint;
import co.edu.uniquindio.clinicaX.infra.security.config.JwtAuthenticationFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import static co.edu.uniquindio.clinicaX.model.enums.Roles.ADMIN;
import static co.edu.uniquindio.clinicaX.model.enums.Roles.PACIENTE;
@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class WebSecurityConfig {

    //private final SecurityFilter securityFilter;
    private final JwtAuthenticationFilter jwtAuthFilter;
    private final JwtAuthenticationEntryPoint jwtEntryPoint;
    private final AuthenticationProvider authenticationProvider;
    private static final String[] adminUrls =
            {
                    "/api/medicos/{id}/crear",
                    "/api/medicos/{id}/actualizar",
                    "/api/medicos/{id}/eliminar",
                    "/api/medicos/{id}/listar",
                    "/api/medicos/find",
                    "/api/pqrs/cambiar_estado",
            };
    private static final String[] pacienteUrls =
            //cada metodo del servicio tiene una Url()para Admin, Paciente, Medico
            {
                    "/api/auth/**",
                    "/api/citas/crear/**",
                    "/api/citas/listar/todos",
                    "/api/citas/listar/nombre/**",
                    "/api/citas/listar/categoria/**",
                    "/api/paciente/crear-cita",
                    "/api/paciente/registro"
            };
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable)
                .cors(Customizer.withDefaults())
                .sessionManagement(sess->sess.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(req->
                    req
                            .requestMatchers(HttpMethod.POST, "/login").permitAll()
                            .requestMatchers(adminUrls).hasRole(ADMIN.name())//diferencia con hasAuthority
                            .requestMatchers(pacienteUrls).hasAnyRole(PACIENTE.name(), ADMIN.name())
                            .anyRequest().authenticated()
                )
                .exceptionHandling(ex -> ex.authenticationEntryPoint(jwtEntryPoint))
                .authenticationProvider(authenticationProvider)
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }/*
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }*/
    @Bean
    public PasswordEncoder bCryptPasswordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
