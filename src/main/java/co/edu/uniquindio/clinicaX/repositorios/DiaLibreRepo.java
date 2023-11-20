package co.edu.uniquindio.clinicaX.repositorios;

import co.edu.uniquindio.clinicaX.dto.medico.DiaLibreDTO;
import co.edu.uniquindio.clinicaX.model.DiaLibre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalTime;
import java.util.List;

@Repository
public interface DiaLibreRepo extends JpaRepository<DiaLibre, Integer> {
    List<DiaLibre> findByMedicoCodigoAndDiaIsNull(int codigoMedico);
    List<DiaLibre> findByMedicoCodigoAndDiaIsNotNull(int codigoMedico);
}
