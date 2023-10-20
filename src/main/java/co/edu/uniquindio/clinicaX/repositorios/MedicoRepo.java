package co.edu.uniquindio.clinicaX.repositorios;

import co.edu.uniquindio.clinicaX.model.*;
import co.edu.uniquindio.clinicaX.model.enums.Especialidad;
import co.edu.uniquindio.clinicaX.model.enums.EstadoUsuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface MedicoRepo extends JpaRepository<Medico, Integer> {

    Medico findByCorreo(String nombre);
    Medico findByCedula(String cedula);
    List<Medico> findAllByEspecialidadAndEstado(Especialidad especialidad, EstadoUsuario estado);

    @Query("""
            select m from Medico m
            where m.estado = co.edu.uniquindio.clinicaX.model.enums.EstadoUsuario.ACTIVO and 
            m.especialidad = :especialidad and
            m.codigo not in(
            select c.medico.codigo from Cita c
            where c.fechaCita= :fecha
            )
            order by rand()
            limit 1
            """)
    Medico seleccionaMedicoConEspecialidadEnFecha(Especialidad especialidad, LocalDateTime fecha);

    @Query("""
            select m from Medico m
            where m.codigo = :idMedico and
            m.estado = co.edu.uniquindio.clinicaX.model.enums.EstadoUsuario.ACTIVO
            """)
    Medico findActivoById(Integer idMedico);
}
