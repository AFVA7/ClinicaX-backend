package co.edu.uniquindio.clinicaX.infra.security;

import co.edu.uniquindio.clinicaX.infra.security.config.JwtAuthenticationEntryPoint;
import co.edu.uniquindio.clinicaX.infra.security.config.JwtAuthenticationFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import static co.edu.uniquindio.clinicaX.model.enums.Roles.*;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class WebSecurityConfig {

    private final JwtAuthenticationFilter jwtAuthFilter;
    private final JwtAuthenticationEntryPoint jwtEntryPoint;
    private final AuthenticationProvider authenticationProvider;
    private static final String[] adminUrls =
            {
                    "/api/admins/**",
            };
    private static final String[] pacienteUrls =
            //cada metodo del servicio tiene una Url()para Admin, Paciente, Medico
            {
                    "/api/pacientes/**",
                    "/api/citas/cancelar",
                    "/api/citas/citas-pendientes-paciente/{codigo}",
            };
    private static final String[] openUrls =
            {
                    "/api/auth/**",
                    "/api/clinica/**",
            };
    private static final String[] medicosUrls =
            {
                    "/api/medicos/**",
                    "/api/atenciones/**",
                    "/api/citas/citas-pendientes-medico/{codigo}",
                    "/api/dia-libre/**",
            };
    private static final String[] pacienteAdmin =
            {
                    "/api/pqrs/**",
                    "/api/mensajes/**",
                    "/api/citas/filtrar-por-medico/{codigoMedico}",
                    "/api/citas/filtrar-por-fecha"
            };
    private static final String[] pacienteAdminMedicos =
            {
                    "/api/citas/listar-historial/{codigo}",
                    "/api/citas/detalle-cita/{codigo}",
            };
    private static final String[] pacienteMedicos =
            {
                    "/api/imagenes/**"
            };
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable)
                .cors(Customizer.withDefaults())
                .sessionManagement(sess->sess.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(req->
                    req
                            .requestMatchers(openUrls).permitAll()
                            .requestMatchers(adminUrls).hasAuthority(ADMIN.name())//diferencia con hasRole
                            .requestMatchers(medicosUrls).hasAuthority(MEDICO.name())
                            .requestMatchers(pacienteUrls).hasAuthority(PACIENTE.name())
                            .requestMatchers(pacienteAdminMedicos).hasAnyAuthority(PACIENTE.name(), ADMIN.name(), MEDICO.name())
                            .requestMatchers(pacienteAdmin).hasAnyAuthority(PACIENTE.name(), ADMIN.name())
                            .requestMatchers(pacienteMedicos).hasAnyAuthority(PACIENTE.name(), MEDICO.name())
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
