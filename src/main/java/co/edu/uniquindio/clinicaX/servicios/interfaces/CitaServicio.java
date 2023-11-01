package co.edu.uniquindio.clinicaX.servicios.interfaces;

import co.edu.uniquindio.clinicaX.dto.cita.*;
import co.edu.uniquindio.clinicaX.dto.medico.RegistroAtencionDTO;
import co.edu.uniquindio.clinicaX.dto.paciente.FiltroBusquedaDTO;

import java.util.List;

public interface CitaServicio {
    //validar: Un paciente no puede tener más de tres citas agendadas al mismo tiempo.
    //Un paciente puede agendar citas
    int agendarCita(AgendarCitaDTO datos) throws Exception;
    //requisito adicional
    void cancelarCita(CancelamientoCitaDTO datos);
    public List<ItemCitaDTO> listarCitas();
    //En todo momento, el paciente podrá ver todo el historial de atenciones que ha tenido
    //citas que ya han pasado, tienen atención o no si la cita se ha cancelado
    List<ItemCitaDTO> listarHistorialPaciente(int codigoPaciente);
    //citas pendientes
    List<ItemCitaDTO> listarCitasPendientesPaciente(int codigoPaciente);
    /*El paciente podrá ver todos los detalles de cada consulta,
    incluyendo notas del médico, diagnóstico y tratamiento.
     */
    DetalleCitaDTO verDetalleCita(int codigoCita);
    //filtrar las citas por med y fecha
    List<ItemCitaDTO> filtrarCitasPorMedico(int codigoMedico);
    List<ItemCitaDTO> filtrarCitasPorFecha(FiltroBusquedaDTO filtroBusquedaDTO);

    //medico
    //Al ingreso, el médico podrá ver las citas que tiene pendientes por ser atendidas.
    List<ItemCitaDTO> filtrarCitaspendientesMedico(int codigoMedico);
    //podrá atender citas del dia actual, validar que solo puede atender citas del dia actual
    //param diagnostico, tratamiento, notas medicas, llegan desde el cliente(formulario)
    int atenderCita(RegistroAtencionDTO datos);
    //todas las consultas que ha realizado un medico
    //param
    List<ItemAtencionDTO> listarTodasCitasMedico(int codigoMedico);

    //admin


}
