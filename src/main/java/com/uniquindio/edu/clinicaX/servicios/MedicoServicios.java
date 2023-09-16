package com.uniquindio.edu.clinicaX.servicios;

import com.uniquindio.edu.clinicaX.model.Paciente;

import java.time.LocalDate;

public interface MedicoServicios {
    void listarCitasPendientes(int codigoCita, LocalDate fecha, Paciente paciente, String estado);
    void atenderCita();
    void listarCitasPaciente();//historial medico
    void agendarDiaLibre();
    void listarCitasRealizadasMedico(); // listado de consultas


}
