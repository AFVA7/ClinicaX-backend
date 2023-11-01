package co.edu.uniquindio.clinicaX;


import co.edu.uniquindio.clinicaX.dto.cita.*;
import co.edu.uniquindio.clinicaX.dto.paciente.FiltroBusquedaDTO;
import co.edu.uniquindio.clinicaX.model.enums.*;
import co.edu.uniquindio.clinicaX.servicios.interfaces.CitaServicio;
import co.edu.uniquindio.clinicaX.servicios.interfaces.MedicoServicio;
import co.edu.uniquindio.clinicaX.servicios.interfaces.PacienteServicio;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;

import java.time.LocalDateTime;
import java.util.List;

@SpringBootTest
@Transactional
public class CitaServicioTest {
    @Autowired
    CitaServicio citaServicios;
    @Autowired
    PacienteServicio pacienteServicio;
    @Autowired
    MedicoServicio medicoServicio;
    @Test
    @Sql("classpath:dataset.sql")
    public void agendarCitaTest() throws Exception {

        AgendarCitaDTO agendarCitaDTO = new AgendarCitaDTO(
                1,
                6,
                LocalDateTime.of(2024,01,10,10,30),
                "motivo",
                Especialidad.PEDIATRIA
        );
        int codigoCita = citaServicios.agendarCita(agendarCitaDTO);
        Assertions.assertNotEquals(0, codigoCita);
    }

    @Test
    @Sql("classpath:dataset.sql")
    public void cancelarCitaTest() {

        CancelamientoCitaDTO datos = new CancelamientoCitaDTO(
                1,
                MotivoCancelamiento.PASIENTE_DESISTIO
        );
        citaServicios.cancelarCita(datos);
        DetalleCitaDTO citaCancelada = citaServicios.verDetalleCita(1);
        Assertions.assertEquals(EstadoCita.CANCELADA, citaCancelada.estado());
    }

    @Test
    @Sql("classpath:dataset.sql")
    public void listarCitasTest() {
        List<ItemCitaDTO> lista = citaServicios.listarCitas();
        lista.forEach(System.out::println);
        Assertions.assertEquals(6, lista.size());
    }


    @Test
    @Sql("classpath:dataset.sql")
    public void listarHistorialPacienteTest() {
        List<ItemCitaDTO> lista = citaServicios.listarHistorialPaciente(1);
        lista.forEach(System.out::println);
        Assertions.assertEquals(1, lista.size());

    }

    @Test
    @Sql("classpath:dataset.sql")
    public void filtrarCitasPorMedicoTest() {
        List<ItemCitaDTO> lista = citaServicios.filtrarCitasPorMedico(6);
        lista.forEach(System.out::println);
        Assertions.assertEquals(2, lista.size());
    }

    @Test
    @Sql("classpath:dataset.sql")
    public void filtrarCitasPorFechaTest() {
        FiltroBusquedaDTO filtro = new FiltroBusquedaDTO(
                1,
                LocalDateTime.of(2023, 10, 1,10,20),
                LocalDateTime.of(2023, 12,1,10,30)

        );
        List<ItemCitaDTO> lista = citaServicios.filtrarCitasPorFecha(filtro);
        lista.forEach(System.out::println);
        Assertions.assertEquals(1, lista.size());
    }
    @Test
    @Sql("classpath:dataset.sql")
    public void filtrarCitaspendientesMedico() {
        List<ItemCitaDTO> lista = citaServicios.filtrarCitasPorMedico(6);
        lista.forEach(System.out::println);
        Assertions.assertEquals(2, lista.size());
    }
    @Test
    @Sql("classpath:dataset.sql")
    public void listarCitasPendientesPacienteTest() {
        List<ItemCitaDTO> lista = citaServicios.listarCitasPendientesPaciente(1);
        lista.forEach(System.out::println);
        Assertions.assertEquals(1, lista.size());

    }
}
