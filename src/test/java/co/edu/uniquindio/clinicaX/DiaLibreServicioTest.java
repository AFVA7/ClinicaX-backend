package co.edu.uniquindio.clinicaX;

import co.edu.uniquindio.clinicaX.dto.medico.DetalleDiaLibreDTO;
import co.edu.uniquindio.clinicaX.dto.medico.DiaLibreDTO;
import co.edu.uniquindio.clinicaX.servicios.interfaces.DiaLibreServicio;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;

import java.time.LocalDate;
import java.util.List;

@SpringBootTest
@Transactional
public class DiaLibreServicioTest {
    @Autowired
    private DiaLibreServicio diaLibreServicios;

    @Test
    public void crearTest() {
        DiaLibreDTO diaLibreDTO = new DiaLibreDTO(
                6,
                LocalDate.of(2023,12,15),
                "motivo"

        );
        int nuevo = diaLibreServicios.crear(diaLibreDTO);
        Assertions.assertNotEquals(0, nuevo);
    }

    @Test
    @Sql("classpath:dataset.sql")
    public void updateTest() throws Exception {
        DetalleDiaLibreDTO guardado = diaLibreServicios.obtener(1);
        DetalleDiaLibreDTO modificado = new DetalleDiaLibreDTO(
                guardado.codigo(),
                guardado.codigoMedico(),
                LocalDate.of(2024,12,15),
                "motivo mofidicado"
        );
        diaLibreServicios.update(modificado);
        DetalleDiaLibreDTO objetoModificado = diaLibreServicios.obtener(1);
        Assertions.assertEquals(LocalDate.of(2024,12,15), objetoModificado.fecha());
    }

    @Test
    @Sql("classpath:dataset.sql")
    public void eliminarTest() throws Exception {
        diaLibreServicios.eliminar(1);
        Assertions.assertThrows(Exception.class, () -> diaLibreServicios.obtener(1));
    }
    @Test
    @Sql("classpath:dataset.sql")
    public void listarTest() {
        List<DetalleDiaLibreDTO> lista = diaLibreServicios.listar();
        lista.forEach(System.out::println);
        Assertions.assertEquals(5, lista.size());
    }
}
