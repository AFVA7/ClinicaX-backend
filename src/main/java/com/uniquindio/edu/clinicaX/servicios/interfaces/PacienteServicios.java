package com.uniquindio.edu.clinicaX.servicios.interfaces;

import com.uniquindio.edu.clinicaX.dto.*;
import com.uniquindio.edu.clinicaX.dto.admin.ItemCitaAdminDTO;
import com.uniquindio.edu.clinicaX.dto.paciente.*;

import java.util.List;

public interface PacienteServicios {
    //servicio de registro

    //Un paciente puede registrarse
    int registrarse(RegistroPacienteDTO registroPacienteDTO) throws Exception;

    //Un paciente ´puede editar su info personal
    int editarPerfil(int codigoPaciente, RegistroPacienteDTO registroPacienteDTO) throws Exception;

    //Un paciente ´puede recuperar su contraseña
    void recuperarPasswd();

    void cambiarPasswd(NuevaPasswordDTO nuevaPasswordDTO)throws Exception;

    void enviarLinkRecuperacion(String email)throws Exception;

    void cambiarPassword();

    //Un paciente ´puede eliminar la cuenta
    int eliminarCuenta(int codigoPaciente)throws Exception;

    DetallePacienteDTO verDetallePaciente(int codigo) throws Exception;




    //servicios de cita
    int agendarCita(RegistroCitaDTO registroCitaDTO) throws Exception;

    List<ItemCitaAdminDTO> listarCitasPaciente(int codigoPaciente) throws Exception;

    void filtrarCitasPorFecha(FiltroBusquedaDTO filtroBusquedaDTO) throws Exception;

    void filtrarCitasPorMedico();

    DetalleAtencionMedicaDTO verDetalleCita(int codigoCita) throws Exception;



    //servicios de PQRS
    int crearPQRS(RegistroPQRDTO registroPQRDTO) throws Exception;

    List<ItemPQRSDTO> listarPQRSPaciente(int codigoPaciente) throws Exception;

    int responderPQRS(RegistroRespuestaDTO registroRespuestaDTO) throws Exception;
    DetallePQRSDTO verDetallePQR(int codigo) throws Exception;


}
