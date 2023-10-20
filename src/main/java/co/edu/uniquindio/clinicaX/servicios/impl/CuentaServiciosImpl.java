package co.edu.uniquindio.clinicaX.servicios.impl;

import co.edu.uniquindio.clinicaX.dto.EmailDTO;
import co.edu.uniquindio.clinicaX.dto.NuevaPasswordDTO;
import co.edu.uniquindio.clinicaX.model.Cuenta;
import co.edu.uniquindio.clinicaX.repositorios.CuentaRepo;
import co.edu.uniquindio.clinicaX.servicios.interfaces.CuentaServicios;
import jakarta.transaction.Transactional;
import jakarta.validation.ValidationException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Base64;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class CuentaServiciosImpl implements CuentaServicios {
    private  final PasswordEncoder passwordEncoder;
    private final CuentaRepo cuentaRepo;
    private final EmailServicioImpl emailServicio;

    @Override
    public void enviarLinkRecuperacion(String email) throws Exception {
        Optional<Cuenta> opcional = cuentaRepo.findByCorreo(email);
        if(opcional.isEmpty()){
            throw new ValidationException("No existe una cuenta con el correo "+email);
        }
        Cuenta cuenta = opcional.get();
        LocalDateTime fecha = LocalDateTime.now();
        String parametro = Base64.getEncoder().encodeToString( (cuenta.getCodigo()+";"+fecha).getBytes() );
        emailServicio.enviarEmail(new EmailDTO(
                cuenta.getCorreo(),
                "Recuperación de contraseña",
                "Hola, para recuperar tu contraseña ingresa al siguiente link: https://api/recuperar-pasword/"+parametro
        ));

    }

    @Override
    public void cambiarPasswd(NuevaPasswordDTO nuevaPasswordDTO) throws Exception {
        String parametro = new String(Base64.getDecoder().decode(nuevaPasswordDTO.nuevaPasswd()));
        String[] datos = parametro.split(";");
        int codigoCuenta = Integer.parseInt(datos[0]);
        LocalDateTime fecha = LocalDateTime.parse(datos[1]);
        if (fecha.plusMinutes(30).isBefore(LocalDateTime.now())){
            throw new Exception("Ell link de recuperación ha expirado");
        }
        Cuenta cuenta = obtenerCuentaCodigo(codigoCuenta);
        cuenta.setPasswd(passwordEncoder.encode(nuevaPasswordDTO.nuevaPasswd()));
    }

    @Override
    public void recuperarPasswd() {

    }

    @Override
    public Cuenta obtenerCuentaCodigo(int codigoCuenta) {
        return null;
    }
}
