package co.edu.uniquindio.clinicaX;

import co.edu.uniquindio.clinicaX.dto.RegistroRespuestaDTO;
import co.edu.uniquindio.clinicaX.dto.pqrs.DetallePQRSDTO;
import co.edu.uniquindio.clinicaX.dto.pqrs.ItemPQRSDTO;
import co.edu.uniquindio.clinicaX.dto.pqrs.RegistroPQRDTO;
import co.edu.uniquindio.clinicaX.model.enums.*;
import co.edu.uniquindio.clinicaX.servicios.interfaces.MensajeServicio;
import co.edu.uniquindio.clinicaX.servicios.interfaces.PQRServicio;
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
public class PQRSServicioTest {
    @Autowired
    private PQRServicio pqrServicio;
    @Autowired
    private MensajeServicio mensajeServicios;

    @Test
    @Sql("classpath:dataset.sql")
    public void crearPQRSPQRS() throws Exception {

        RegistroPQRDTO datos = new RegistroPQRDTO(
                1,
                "motivo",
                1,
                TipoPQRS.QUEJA
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
    @Sql("classpath:dataset.sql")
    public void responderPQRSTest() throws Exception {
        RegistroRespuestaDTO datosParaElNuevoMsj = new RegistroRespuestaDTO(
                1,
                1,
                5,
                "Como estás?, soy un admin"

        );
        //mensaje que se crea
        int nuevo = pqrServicio.responderPQRS(datosParaElNuevoMsj);
        System.out.println(mensajeServicios.obtener(nuevo).mensajesAsociados());
        System.out.println(mensajeServicios.obtener(nuevo).contenido());
        Assertions.assertNotEquals(0, nuevo);
    }
}
