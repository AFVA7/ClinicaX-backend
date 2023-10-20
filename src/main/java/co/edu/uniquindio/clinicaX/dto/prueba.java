package co.edu.uniquindio.clinicaX.dto;

import co.edu.uniquindio.clinicaX.ClinicaXApplication;
import co.edu.uniquindio.clinicaX.dto.paciente.DetallePacienteDTO;
import co.edu.uniquindio.clinicaX.dto.paciente.ItemPacienteDTO;
import co.edu.uniquindio.clinicaX.model.Paciente;
import co.edu.uniquindio.clinicaX.model.enums.EstadoUsuario;
import co.edu.uniquindio.clinicaX.repositorios.PacienteRepo;
import co.edu.uniquindio.clinicaX.servicios.impl.CuentaServiciosImpl;
import co.edu.uniquindio.clinicaX.servicios.impl.PacienteServicioImpl;
import jakarta.transaction.Transactional;
import jakarta.validation.ValidationException;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
@Transactional
public class prueba {
    private final PacienteRepo pacienteRepo;
    private final CuentaServiciosImpl cuentaServicios;
    public static void main(String[] args) throws Exception {
        ConfigurableApplicationContext context = SpringApplication.run(ClinicaXApplication.class, args);

        prueba prueba = context.getBean(prueba.class);
        prueba.imprimir();

    }
    public void imprimir() throws Exception {
        cuentaServicios.cambiarPasswd(new NuevaPasswordDTO(1, "1238766867678545"));
    }

}
