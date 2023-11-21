package co.edu.uniquindio.clinicaX;

import co.edu.uniquindio.clinicaX.dto.admin.DetalleMedicoDTO;
import co.edu.uniquindio.clinicaX.dto.admin.HorarioDTO;
import co.edu.uniquindio.clinicaX.dto.admin.ItemMedicoDto;
import co.edu.uniquindio.clinicaX.dto.admin.RegistroMedicoDTO;
import co.edu.uniquindio.clinicaX.dto.cita.ItemCitaDTO;
import co.edu.uniquindio.clinicaX.dto.medico.DiaLibreDTO;
import co.edu.uniquindio.clinicaX.model.enums.*;
import co.edu.uniquindio.clinicaX.servicios.interfaces.MedicoServicio;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@SpringBootTest
@Transactional
public class MedicoServicioTest {
    @Autowired
    private MedicoServicio medicoServicio;

    @Test
    public void crearMedicoTest() {
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
        int nuevo = medicoServicio.crearMedico(medicoDTO);
        Assertions.assertNotEquals(0, nuevo);
    }

    @Test
    @Sql("classpath:dataset.sql")
    public void acualizarMedico() throws Exception {
        DetalleMedicoDTO guardado = medicoServicio.obtenerMedico(6);
        DetalleMedicoDTO modificado = new DetalleMedicoDTO(
                guardado.codigo(),
                guardado.cedula(),
                guardado.nombre(),
                guardado.ciudad(),
                guardado.especialidad(),
                "111111",
                guardado.correo(),
                guardado.urlFoto(),
        guardado.horarios()
        );

        medicoServicio.acualizarMedico(modificado);
        DetalleMedicoDTO objetoModificado = medicoServicio.obtenerMedico(6);
        Assertions.assertEquals("111111", objetoModificado.telefono());
    }

    @Test
    @Sql("classpath:dataset.sql")
    public void eliminarMedico() throws Exception {
        medicoServicio.eliminarMedico(6);
        Assertions.assertThrows(Exception.class, () -> medicoServicio.obtenerMedico(6));
    }

    @Test
    @Sql("classpath:dataset.sql")
    public void listarMedicos() throws Exception {
        List<ItemMedicoDto> lista = medicoServicio.listarMedicos();
        lista.forEach(System.out::println);
        Assertions.assertEquals(5, lista.size());
    }
    @Test
    @Sql("classpath:dataset.sql")
    public void listarCitasPendientesTest() {
        List<ItemCitaDTO> lista = medicoServicio.listarCitasPendientes(6);
        lista.forEach(System.out::println);
        Assertions.assertEquals(2, lista.size());
    }

    @Test
    @Sql("classpath:dataset.sql")
    public void agendarDiaLibreTest() throws Exception {
        DiaLibreDTO diaLibreDTO = new DiaLibreDTO(
                6,
                LocalDate.of(2023,12,30),
                "motivo"

        );
        int dia = medicoServicio.agendarDiaLibre(diaLibreDTO);
        Assertions.assertNotEquals(0, dia);
    }
    @Test
    @Sql("classpath:dataset.sql")
    public void listarCitasRealizadasMedicoTest() throws Exception {
        List<ItemCitaDTO> lista = medicoServicio.listarCitasRealizadasMedico(6);
        lista.forEach(System.out::println);
        Assertions.assertEquals(2, lista.size());
    }

}
