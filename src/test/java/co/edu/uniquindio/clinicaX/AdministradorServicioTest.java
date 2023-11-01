package co.edu.uniquindio.clinicaX;

import co.edu.uniquindio.clinicaX.dto.admin.HorarioDTO;
import co.edu.uniquindio.clinicaX.dto.admin.RegistroMedicoDTO;
import co.edu.uniquindio.clinicaX.model.enums.Ciudad;
import co.edu.uniquindio.clinicaX.model.enums.Especialidad;
import co.edu.uniquindio.clinicaX.servicios.interfaces.AdministradorServicio;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@SpringBootTest
@Transactional//para que al ejecutar los métodos de test no modifiquen la base de datos de MySQL.
public class AdministradorServicioTest {

    @Autowired
    private AdministradorServicio administradorServicio;


    @Test
    public void crearMedicoTest(){

        List<HorarioDTO> horarios = new ArrayList<>();
        horarios.add( new HorarioDTO("LUNES", LocalTime.of(7, 0, 0), LocalTime.of(14, 0, 0) ) );

        RegistroMedicoDTO medicoDTO = new RegistroMedicoDTO(
                "Pepito",
                "82872",
                Ciudad.ARMENIA,
                Especialidad.CARDIOLOGIA,
                "78387",
                "pepito@email.com",
                "123a",
                "url_foto",
                horarios
        );

        try {
            administradorServicio.crearMedico(medicoDTO);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
