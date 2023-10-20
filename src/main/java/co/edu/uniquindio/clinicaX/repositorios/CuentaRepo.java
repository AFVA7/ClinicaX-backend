package co.edu.uniquindio.clinicaX.repositorios;

import co.edu.uniquindio.clinicaX.model.Cuenta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CuentaRepo extends JpaRepository<Cuenta, Integer> {

    Optional<Cuenta> findByCorreo(String username);
}
