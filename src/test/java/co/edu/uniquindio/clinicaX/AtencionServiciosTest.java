package co.edu.uniquindio.clinicaX;

import co.edu.uniquindio.clinicaX.dto.admin.HorarioDTO;
import co.edu.uniquindio.clinicaX.dto.admin.RegistroMedicoDTO;
import co.edu.uniquindio.clinicaX.dto.cita.AgendarCitaDTO;
import co.edu.uniquindio.clinicaX.dto.cita.DetalleAtencionMedicaDTO;
import co.edu.uniquindio.clinicaX.dto.cita.DetalleCitaDTO;
import co.edu.uniquindio.clinicaX.dto.cita.ItemAtencionDTO;
import co.edu.uniquindio.clinicaX.dto.medico.RegistroAtencionDTO;
import co.edu.uniquindio.clinicaX.dto.paciente.RegistroPacienteDTO;
import co.edu.uniquindio.clinicaX.model.enums.*;
import co.edu.uniquindio.clinicaX.servicios.impl.AtencionServicioImpl;
import co.edu.uniquindio.clinicaX.servicios.impl.CitaServiciosImpl;
import co.edu.uniquindio.clinicaX.servicios.impl.MedicoServicioImpl;
import co.edu.uniquindio.clinicaX.servicios.impl.PacienteServicioImpl;
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

@SpringBootTest
@Transactional
public class AtencionServiciosTest {
    @Autowired
    private AtencionServicioImpl atencionServicio;
    @Autowired
    private CitaServiciosImpl citaServicios;
    @Autowired
    private PacienteServicioImpl pacienteServicio;
    @Autowired
    private MedicoServicioImpl medicoServicio;

    @Test
    public void crearAtencionTest() throws Exception {
        List<HorarioDTO> horarios = new ArrayList<>();
        int paciente = pacienteServicio.registrarse(new RegistroPacienteDTO("24567234", "Pepito Perez", "5454545", "url_foto",Ciudad.ARMENIA, LocalDate.of(2000,10,10), "Sin alergias", Eps.ALIANSALUD,TipoSangre.A_NEGATIVO, "pepito@email.com", "123"));
        int medico = medicoServicio.crearMedico(new RegistroMedicoDTO("Zayra Parra", "768786", Ciudad.ARMENIA, Especialidad.PEDIATRIA,"879896", "zay@gmail.com","111", "url-foto",horarios));
        DetalleCitaDTO cita = citaServicios.agendarCita(new AgendarCitaDTO(paciente,medico,LocalDateTime.now(), LocalDateTime.of(2023,10,30,10,30), "motivo", Especialidad.PSIQUIATRIA));

        RegistroAtencionDTO atencionDTO = new RegistroAtencionDTO(
                cita.codigoCita(),
                medico,
                "notas",
                "tratamiento",
                "diagnostico");
        int nuevo = atencionServicio.crear(atencionDTO);
        System.out.println(atencionServicio.verDetalle(nuevo));
        Assertions.assertNotEquals(0, nuevo);
    }

    @Test
    @Sql("classpath:dataset.sql")
    public void updateTest() throws Exception {
        DetalleAtencionMedicaDTO guardado = atencionServicio.verDetalle(1);
        DetalleAtencionMedicaDTO modificado = new DetalleAtencionMedicaDTO(
                guardado.codigo(),
                guardado.codigoCita(),
                guardado.nombrePaciente(),
                guardado.nombreMedico(),
                guardado.especialidad(),
                guardado.fechaAtencion(),
                "otro tratamiento",
                guardado.notasMedicas(),
                guardado.diagnostico(),
                guardado.estadoCita()
        );
        atencionServicio.update(modificado);
        DetalleAtencionMedicaDTO objetoModificado = atencionServicio.verDetalle(1);
        Assertions.assertEquals("otro tratamiento", objetoModificado.tratamiento());
    }

    @Test
    @Sql("classpath:dataset.sql")
    public void eliminarTest() throws Exception {
        atencionServicio.eliminar(1);
        Assertions.assertThrows(Exception.class, () -> atencionServicio.verDetalle(1));
    }
    @Test
    @Sql("classpath:dataset.sql")
    public void listarTest() {
        List<ItemAtencionDTO> lista = atencionServicio.listar(6);
        lista.forEach(System.out::println);
        Assertions.assertEquals(2, lista.size());
    }
}
