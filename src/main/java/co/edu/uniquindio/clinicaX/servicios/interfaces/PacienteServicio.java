package co.edu.uniquindio.clinicaX.servicios.interfaces;

import co.edu.uniquindio.clinicaX.dto.*;
import co.edu.uniquindio.clinicaX.dto.cita.AgendarCitaDTO;
import co.edu.uniquindio.clinicaX.dto.cita.DetalleAtencionMedicaDTO;
import co.edu.uniquindio.clinicaX.dto.cita.ItemCitaDTO;
import co.edu.uniquindio.clinicaX.dto.paciente.*;
import co.edu.uniquindio.clinicaX.dto.pqrs.DetallePQRSDTO;
import co.edu.uniquindio.clinicaX.dto.pqrs.ItemPQRSDTO;
import co.edu.uniquindio.clinicaX.dto.pqrs.RegistroPQRDTO;

import java.util.List;

public interface PacienteServicio {
    //servicio de registro

    //Un paciente puede registrarse
    int registrarse(RegistroPacienteDTO registroPacienteDTO);

    //Un paciente ´puede editar su info personal
    int editarPerfil(DetallePacienteDTO datos);

    List<ItemPacienteDTO > listarTodos();

    //Un paciente ´puede recuperar su contraseña
    void recuperarPasswd(String email)throws Exception;

    //Un paciente ´puede eliminar la cuenta
    String eliminarCuenta(int codigoPaciente)throws Exception;

    void activarPaciente(int codigo);

    DetallePacienteDTO verDetallePaciente(int codigo) throws Exception;




    //servicios de cita
    int agendarCita(AgendarCitaDTO agendarCitaDTO) throws Exception;

    List<ItemCitaDTO> listarHistorial(int codigoPaciente) throws Exception;
    List<ItemCitaDTO> listarCitasPendientes(int codigoPaciente);

    List<ItemCitaDTO> filtrarCitasPorFecha(FiltroBusquedaDTO filtroBusquedaDTO);

    List<ItemCitaDTO> filtrarCitasPorMedico(int idMedico);

    DetalleAtencionMedicaDTO verDetalleCita(int codigoCita) throws Exception;



    //servicios de PQRS
    void crearPQRS(RegistroPQRDTO registroPQRDTO) throws Exception;
    //en ttodo momento el paciente podrá ver el historial de las peticiones
    List<ItemPQRSDTO> listarPQRSPaciente(int codigoPaciente);

    int responderPQRS(RegistroRespuestaDTO registroRespuestaDTO) throws Exception;
    DetallePQRSDTO verDetallePQR(int codigo) throws Exception;





}
