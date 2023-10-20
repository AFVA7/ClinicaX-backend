package co.edu.uniquindio.clinicaX;

import co.edu.uniquindio.clinicaX.dto.RegistroRespuestaDTO;
import co.edu.uniquindio.clinicaX.dto.admin.DetalleMedicoDTO;
import co.edu.uniquindio.clinicaX.dto.admin.HorarioDTO;
import co.edu.uniquindio.clinicaX.dto.admin.ItemMedicoDto;
import co.edu.uniquindio.clinicaX.dto.admin.RegistroMedicoDTO;
import co.edu.uniquindio.clinicaX.dto.cita.ItemAtencionDTO;
import co.edu.uniquindio.clinicaX.dto.cita.ItemCitaDTO;
import co.edu.uniquindio.clinicaX.dto.pqrs.DetallePQRSDTO;
import co.edu.uniquindio.clinicaX.dto.pqrs.ItemPQRSDTO;
import co.edu.uniquindio.clinicaX.model.enums.Ciudad;
import co.edu.uniquindio.clinicaX.model.enums.Especialidad;
import co.edu.uniquindio.clinicaX.model.enums.EstadoPQRS;
import co.edu.uniquindio.clinicaX.servicios.interfaces.AdministradorServicios;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@SpringBootTest
@Transactional//para que al ejecutar los métodos de test no modifiquen la base de datos de MySQL.
public class AdministradorServiciosTest {

    @Autowired
    private AdministradorServicios administradorServicio;


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
