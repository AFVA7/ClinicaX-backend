package co.edu.uniquindio.clinicaX.dto;

import co.edu.uniquindio.clinicaX.ClinicaXApplication;
import co.edu.uniquindio.clinicaX.dto.paciente.NuevaPasswordDTO;
import co.edu.uniquindio.clinicaX.repositorios.PacienteRepo;
import co.edu.uniquindio.clinicaX.servicios.impl.CuentaServicioImpl;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Transactional
public class prueba {
    private final PacienteRepo pacienteRepo;
    private final CuentaServicioImpl cuentaServicios;
    public static void main(String[] args) throws Exception {
        ConfigurableApplicationContext context = SpringApplication.run(ClinicaXApplication.class, args);

        prueba prueba = context.getBean(prueba.class);
        prueba.imprimir();

    }
    public void imprimir() throws Exception {
        cuentaServicios.cambiarPasswd(new NuevaPasswordDTO(1, "1238766867678545"));
    }

}
