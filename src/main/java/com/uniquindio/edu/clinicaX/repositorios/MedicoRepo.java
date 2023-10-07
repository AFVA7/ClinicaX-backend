package com.uniquindio.edu.clinicaX.repositorios;

import com.uniquindio.edu.clinicaX.model.Especialidad;
import com.uniquindio.edu.clinicaX.model.EstadoUsuario;
import com.uniquindio.edu.clinicaX.model.Medico;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MedicoRepo extends JpaRepository<Medico, Integer> {

    Medico findByCorreo(String nombre);
    Medico findByCedula(String cedula);
    List<Medico> findAllByEspecialidadAndEstado(Especialidad especialidad, EstadoUsuario estado);
}
