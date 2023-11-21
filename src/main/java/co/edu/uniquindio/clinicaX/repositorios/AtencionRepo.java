package co.edu.uniquindio.clinicaX.repositorios;

import co.edu.uniquindio.clinicaX.model.Atencion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AtencionRepo extends JpaRepository<Atencion, Integer> {
    Optional<Atencion> findAtencionByCitaCodigo(int codigoCita);
    //todas las atenciones asociadas a un médico a través de una cita específica
    List<Atencion> findAllByCita_MedicoCodigo(int codigoMedico);

    Boolean existsByCitaCodigo(int codigoCita);
}
