package co.edu.uniquindio.clinicaX.infra.security.servicios;

import co.edu.uniquindio.clinicaX.infra.security.model.UserDetailsImpl;
import co.edu.uniquindio.clinicaX.model.Administrador;
import co.edu.uniquindio.clinicaX.model.Cuenta;
import co.edu.uniquindio.clinicaX.model.Paciente;
import co.edu.uniquindio.clinicaX.model.Usuario;
import co.edu.uniquindio.clinicaX.repositorios.AdminRepo;
import co.edu.uniquindio.clinicaX.repositorios.CuentaRepo;
import co.edu.uniquindio.clinicaX.repositorios.MedicoRepo;
import co.edu.uniquindio.clinicaX.repositorios.PacienteRepo;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Transactional
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    private CuentaRepo cuentaRepo;


    @Autowired
    private AdminRepo adminRepo;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<Cuenta> cuenta = cuentaRepo.findByCorreo(email);

        if(cuenta.isEmpty()){
                throw new UsernameNotFoundException("El usuario no existe");
        }else{
            return UserDetailsImpl.build(cuenta.get());
        }

    }
}
