package co.edu.uniquindio.clinicaX;

import co.edu.uniquindio.clinicaX.servicios.impl.EmailServicioImpl;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Transactional
public class EmailServiciosTest {
    @Autowired
    EmailServicioImpl emailServicio;
}
