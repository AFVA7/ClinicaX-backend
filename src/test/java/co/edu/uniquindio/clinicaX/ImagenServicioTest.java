package co.edu.uniquindio.clinicaX;

import co.edu.uniquindio.clinicaX.servicios.impl.ImagenServicioImpl;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Transactional
public class ImagenServicioTest {
    @Autowired
    ImagenServicioImpl imagenServicio;
}
