package co.edu.uniquindio.clinicaX.repositorios;

import co.edu.uniquindio.clinicaX.model.Atencion;
import co.edu.uniquindio.clinicaX.model.Cita;
import co.edu.uniquindio.clinicaX.model.Medico;
import co.edu.uniquindio.clinicaX.model.enums.EstadoCita;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface CitaRepo extends JpaRepository<Cita, Integer> {
    @Query("""
            select c from Cita c
            where c.paciente.codigo = :codigoPaciente and 
            c.estado != co.edu.uniquindio.clinicaX.model.enums.EstadoCita.PROGRAMADA
            """)
    List<Cita> findHistorial(int codigoPaciente);
    List<Cita> findCitaByPacienteCodigoAndFechaCitaBetween(int codigoPaciente, LocalDateTime fechaCita, LocalDateTime fechaCita2);
    List<Cita> findAllByMedicoCodigo(int codigoMedico);
    List<Cita> findAllByPacienteCodigo(int codigoPaciente);
    Boolean existsByPacienteCodigoAndFechaCitaBetweenAndEstadoNot(
            Integer idPaciente, LocalDateTime primerHorario, LocalDateTime ultimoHorario, EstadoCita estadoCita);
    Boolean existsByMedicoCodigoAndFechaCita(
            Integer idMedico, LocalDateTime fecha
    );
    Integer countByPacienteCodigoAndEstado(Integer idPaciente, EstadoCita estado);

    List<Cita> findByEstadoAndMedicoCodigo(EstadoCita estado, int codigoMedico);
    List<Cita> findByEstadoAndPacienteCodigo(EstadoCita estado, int codigoPaciente);
    // relación entre Cita y Medico a través de Atencion
    List<Cita> findAllByAtencionCitaMedicoCodigo(int codigoMedico);

    Boolean existsByMedicoCodigoAndFechaCitaBetween(int codigo, LocalDateTime primerHorario, LocalDateTime ultimoHorario);
    Boolean existsByCodigoAndEstado(int codigo, EstadoCita estado);
}
