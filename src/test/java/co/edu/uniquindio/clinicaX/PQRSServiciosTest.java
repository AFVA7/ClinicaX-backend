package co.edu.uniquindio.clinicaX;

import co.edu.uniquindio.clinicaX.dto.RegistroRespuestaDTO;
import co.edu.uniquindio.clinicaX.dto.admin.HorarioDTO;
import co.edu.uniquindio.clinicaX.dto.admin.RegistroMedicoDTO;
import co.edu.uniquindio.clinicaX.dto.cita.AgendarCitaDTO;
import co.edu.uniquindio.clinicaX.dto.cita.DetalleCitaDTO;
import co.edu.uniquindio.clinicaX.dto.paciente.RegistroPacienteDTO;
import co.edu.uniquindio.clinicaX.dto.pqrs.DetallePQRSDTO;
import co.edu.uniquindio.clinicaX.dto.pqrs.ItemPQRSDTO;
import co.edu.uniquindio.clinicaX.dto.pqrs.RegistroPQRDTO;
import co.edu.uniquindio.clinicaX.model.enums.*;
import co.edu.uniquindio.clinicaX.servicios.impl.*;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@SpringBootTest
@Transactional
public class PQRSServiciosTest {
    @Autowired
    private PQRServicioImpl pqrServicio;
    @Autowired
    private PacienteServicioImpl pacienteServicio;
    @Autowired
    private MedicoServicioImpl medicoServicio;
    @Autowired
    private CitaServiciosImpl citaServicios;
    @Autowired
    private MensajeServiciosImpl mensajeServicios;

    @Test
    public void crearPQRSPQRS() {
        List<HorarioDTO> horarios = new ArrayList<>();
        int paciente = pacienteServicio.registrarse(new RegistroPacienteDTO("24567234", "Pepito Perez", "5454545", "url_foto", Ciudad.ARMENIA, LocalDate.of(2000,10,10), "Sin alergias", Eps.ALIANSALUD, TipoSangre.A_NEGATIVO, "pepito@email.com", "123"));
        int medico = medicoServicio.crearMedico(new RegistroMedicoDTO("Zayra Parra", "768786", Ciudad.ARMENIA, Especialidad.PEDIATRIA,"879896", "zay@gmail.com","111", "url-foto",horarios));
        DetalleCitaDTO cita = citaServicios.agendarCita(new AgendarCitaDTO(paciente,medico,LocalDateTime.now(), LocalDateTime.of(2023,10,30,10,30), "motivo", Especialidad.PSIQUIATRIA));
        RegistroPQRDTO datos = new RegistroPQRDTO(
                cita.codigoCita(),
                "motivo",
                paciente,
                "tipo",
                EstadoPQRS.NUEVO,
                LocalDateTime.now()

        );
        int nuevo = pqrServicio.crearPQRS(datos);
        Assertions.assertNotEquals(0, nuevo);
    }
    @Test
    @Sql("classpath:dataset.sql")
    public void cambiarEstadoPQRS() {
        DetallePQRSDTO guardado = pqrServicio.verDetallePQRS(1);
        pqrServicio.cambiarEstadoPQRS(
                guardado.codigo(),
                EstadoPQRS.RESUELTO
        );
        DetallePQRSDTO objetoModificado = pqrServicio.verDetallePQRS(1);
        Assertions.assertEquals(EstadoPQRS.RESUELTO, objetoModificado.estado());
    }

    @Test
    @Sql("classpath:dataset.sql")
    public void listarPQRS() {
        List<ItemPQRSDTO> lista = pqrServicio.listarPQRS();
        lista.forEach(System.out::println);
        Assertions.assertEquals(5, lista.size());
    }

    @Test
    //@Sql("classpath:dataset.sql")
    public void responderPQRSTest() {
        List<HorarioDTO> horarios = new ArrayList<>();
        int paciente = pacienteServicio.registrarse(new RegistroPacienteDTO("24567234", "Pepito Perez", "5454545", "url_foto", Ciudad.ARMENIA, LocalDate.of(2000,10,10), "Sin alergias", Eps.ALIANSALUD, TipoSangre.A_NEGATIVO, "pepito@email.com", "123"));
        int medico = medicoServicio.crearMedico(new RegistroMedicoDTO("Zayra Parra", "768786", Ciudad.ARMENIA, Especialidad.PEDIATRIA,"879896", "zay@gmail.com","111", "url-foto",horarios));
        DetalleCitaDTO cita = citaServicios.agendarCita(new AgendarCitaDTO(paciente,medico,LocalDateTime.now(), LocalDateTime.of(2023,10,30,10,30), "motivo", Especialidad.PSIQUIATRIA));
        int pqrs = pqrServicio.crearPQRS(new RegistroPQRDTO(cita.codigoCita(),"Hola soy un paciente", paciente, "tipo",EstadoPQRS.ENPROCESO, LocalDateTime.now()));
        RegistroRespuestaDTO datosParaElNuevoMsj = new RegistroRespuestaDTO(
                paciente,
                pqrs,
                1,
                "Como estás?, soy un admin"

        );
        //mensaje que se crea
        int nuevo = pqrServicio.responderPQRS(datosParaElNuevoMsj);
        System.out.println(nuevo);
        String primerMensaje = mensajeServicios.obtener(nuevo).mensajesAsociados();
        String segundoMensaje = mensajeServicios.obtener(nuevo).contenido();
        System.out.println(primerMensaje);
        System.out.println(segundoMensaje);
        Assertions.assertNotEquals(0, nuevo);
    }
}
