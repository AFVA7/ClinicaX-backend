package co.edu.uniquindio.clinicaX.repositorios;

import co.edu.uniquindio.clinicaX.model.Medico;
import co.edu.uniquindio.clinicaX.model.enums.EstadoUsuario;
import co.edu.uniquindio.clinicaX.model.Paciente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface PacienteRepo extends JpaRepository<Paciente, Integer> {

    Paciente findByCorreo(String nombre);
    Paciente findByCedula(String cedula);
    @Query("""
            select p from Paciente p
            where p.codigo = :idPaciente
            and p.estado = co.edu.uniquindio.clinicaX.model.enums.EstadoUsuario.ACTIVO
            """)
    Paciente findActivoById(Integer idPaciente);
}
