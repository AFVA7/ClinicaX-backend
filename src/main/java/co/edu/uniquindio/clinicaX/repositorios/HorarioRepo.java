package co.edu.uniquindio.clinicaX.repositorios;

import co.edu.uniquindio.clinicaX.model.HorarioMedico;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface HorarioRepo extends JpaRepository<HorarioMedico, Integer> {

    List<HorarioMedico> findAllByMedicoCodigo(int codigo);

}
