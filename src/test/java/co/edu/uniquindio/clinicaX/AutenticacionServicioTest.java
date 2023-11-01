package co.edu.uniquindio.clinicaX;

import co.edu.uniquindio.clinicaX.dto.LoginDTO;
import co.edu.uniquindio.clinicaX.dto.security.TokenDTO;
import co.edu.uniquindio.clinicaX.servicios.interfaces.AutenticacionServicio;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;

@SpringBootTest
@Transactional
public class AutenticacionServicioTest {
    @Autowired
    AutenticacionServicio sesionServicio;

    @Test
    @Sql("classpath:dataset.sql")
    public void loginTest(){
        LoginDTO loginDTO = new LoginDTO(
                "zayra@email.com",
                "222"
        );
        TokenDTO tokenDTO = sesionServicio.login(loginDTO);
        System.out.println(tokenDTO.token());
        Assertions.assertNotEquals(null, tokenDTO.token());

    }
}
