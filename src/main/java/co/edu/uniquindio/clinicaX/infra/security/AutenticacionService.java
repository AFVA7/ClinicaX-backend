/*
package co.edu.uniquindio.clinicaX.infra.security;

import co.edu.uniquindio.clinicaX.repositorios.CuentaRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class AutenticacionService implements UserDetailsService {
    private final CuentaRepo cuentaRepo;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return cuentaRepo.findByCorreo(username);
    }
}
*/
