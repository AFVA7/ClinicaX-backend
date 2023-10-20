package co.edu.uniquindio.clinicaX.repositorios;

import co.edu.uniquindio.clinicaX.model.HorarioMedico;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HorarioMedicoRepo extends JpaRepository<HorarioMedico, Integer> {
}
