package co.edu.uniquindio.clinicaX.repositorios;

import co.edu.uniquindio.clinicaX.model.Atencion;
import co.edu.uniquindio.clinicaX.model.Cita;
import co.edu.uniquindio.clinicaX.model.enums.EstadoCita;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface CitaRepo extends JpaRepository<Cita, Integer> {
    List<Cita> findAllByPacienteCodigo(int codigoPaciente);
    List<Cita> findCitaByPacienteCodigoAndFechaCitaBetween(int codigoPaciente, LocalDateTime fechaCita, LocalDateTime fechaCita2);
    List<Cita> findAllByMedicoCodigo(int codigoMedico);
    Boolean existsByPacienteCodigoAndFechaCitaBetween(
            Integer idPaciente, LocalDateTime primerHorario, LocalDateTime ultimoHorario);
    Boolean existsByMedicoCodigoAndFechaCita(
            Integer idMedico, LocalDateTime fecha
    );
    Integer countByPacienteCodigoAndEstado(Integer idPaciente, EstadoCita estado);

    List<Cita> findByEstadoAndMedicoCodigo(EstadoCita estado, int codigoMedico);
    List<Cita> findByEstadoAndPacienteCodigo(EstadoCita estado, int codigoPaciente);
    // relación entre Cita y Medico a través de Atencion
    List<Cita> findAllByAtencionCitaMedicoCodigo(int codigoMedico);

}
