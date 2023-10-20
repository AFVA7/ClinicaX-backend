package co.edu.uniquindio.clinicaX;

import co.edu.uniquindio.clinicaX.dto.security.SesionDTO;
import co.edu.uniquindio.clinicaX.dto.security.TokenDTO;
import co.edu.uniquindio.clinicaX.servicios.impl.SesionServicioImpl;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;

@SpringBootTest
@Transactional
public class AutenticacionServiciosTest {
    @Autowired
    SesionServicioImpl sesionServicio;

    @Test
    @Sql("classpath:dataset.sql")
    public void loginTest(){
        SesionDTO sesionDTO = new SesionDTO(
                "zayra@email.com",
                "222"
        );
        TokenDTO tokenDTO = sesionServicio.login(sesionDTO);
        System.out.println(tokenDTO.getToken());
        Assertions.assertNotEquals(null, tokenDTO.getToken());

    }
}
