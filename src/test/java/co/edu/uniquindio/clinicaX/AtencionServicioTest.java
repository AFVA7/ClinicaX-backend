package co.edu.uniquindio.clinicaX;

import co.edu.uniquindio.clinicaX.dto.cita.DetalleAtencionMedicaDTO;
import co.edu.uniquindio.clinicaX.dto.cita.ItemAtencionDTO;
import co.edu.uniquindio.clinicaX.dto.medico.RegistroAtencionDTO;
import co.edu.uniquindio.clinicaX.servicios.interfaces.AtencionServicio;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;

import java.util.List;

@SpringBootTest
@Transactional
public class AtencionServicioTest {
    @Autowired
    private AtencionServicio atencionServicio;

    @Test
    @Sql("classpath:dataset.sql")
    //cuando el medico atiende una cita, se crea una atención
    public void crearAtencionTest() throws Exception {
        RegistroAtencionDTO atencionDTO = new RegistroAtencionDTO(
                6,//cita que no está atendida
                6,
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
