package com.uniquindio.edu.clinicaX.servicios.interfaces;

public interface CitaServicios {
    //validar: Un paciente no puede tener más de tres citas agendadas al mismo tiempo.
    //Un paciente puede agendar citas
    void agendarCita();
    //requisito adicional
    void cancelarCita();
    //En todo momento, el paciente podrá ver todo el historial de atenciones que ha tenido
    //citas que ya han pasado, tienen atención o no si la cita se ha cancelado
    void listarHistorialPaciente(int codigoPaciente);
    //citas pendientes
    void listarCitasPendientes(int codigoPaciente);
    /*El paciente podrá ver todos los detalles de cada consulta,
    incluyendo notas del médico, diagnóstico y tratamiento.
     */
    void verDetalleCita();
    //filtrar las citas por med y fecha
    void filtrarCitasPorMedico();
    void filtrarCitasPorFecha();

    //medico
    //Al ingreso, el médico podrá ver las citas que tiene pendientes por ser atendidas.
    void filtrarCitaspendientesMedico();
    //podrá atender citas del dia actual, validar que solo puede atender citas del dia actual
    //param diagnostico, tratamiento, notas medicas, llegan desde el cliente(formulario)
    void atenderCita();
    //todas las consultas que ha realizado un medico
    //param
    void listarTodasCitasMedico(int codigoMedico);

    //admin


}
