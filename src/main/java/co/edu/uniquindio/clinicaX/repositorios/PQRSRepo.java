package co.edu.uniquindio.clinicaX.repositorios;

import co.edu.uniquindio.clinicaX.model.Cita;
import co.edu.uniquindio.clinicaX.model.Mensaje;
import co.edu.uniquindio.clinicaX.model.Pqrs;
import co.edu.uniquindio.clinicaX.model.enums.EstadoCita;
import co.edu.uniquindio.clinicaX.model.enums.EstadoPQRS;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PQRSRepo extends JpaRepository<Pqrs, Integer> {
    List<Pqrs> findAllByCita_PacienteCodigo(int codigoPaciente);
    Integer countByCita_PacienteCodigoOrEstadoAndEstado(Integer idPaciente, EstadoPQRS estado1, EstadoPQRS estado2);
    //List<Mensaje> findAllByMensajesCuentaCodigo(int codigoPaciente);
}
