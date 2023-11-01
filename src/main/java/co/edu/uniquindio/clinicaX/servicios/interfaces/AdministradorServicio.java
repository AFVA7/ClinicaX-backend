package co.edu.uniquindio.clinicaX.servicios.interfaces;

import co.edu.uniquindio.clinicaX.dto.cita.ItemAtencionDTO;
import co.edu.uniquindio.clinicaX.dto.pqrs.DetallePQRSDTO;
import co.edu.uniquindio.clinicaX.dto.pqrs.ItemPQRSDTO;
import co.edu.uniquindio.clinicaX.dto.RegistroRespuestaDTO;
import co.edu.uniquindio.clinicaX.dto.admin.*;
import co.edu.uniquindio.clinicaX.dto.cita.ItemCitaDTO;
import co.edu.uniquindio.clinicaX.model.enums.EstadoPQRS;

import java.util.List;

public interface AdministradorServicio {
    //admin puede gestionar médicos
    int crearMedico(RegistroMedicoDTO medico);
    String acualizarMedico(DetalleMedicoDTO medico) throws Exception;
    String eliminarMedico(int codigo) throws Exception;
    List<ItemMedicoDto> listarMedicos() throws Exception;
    List<ItemPQRSDTO>ListarPQRS() throws Exception;
    DetallePQRSDTO verDetellaesPQRS(int codigo) throws Exception;
    List<ItemCitaDTO>listarCitas()throws Exception;
    List<ItemAtencionDTO> historialDeConsultas(int codigoMedico);
    int responderPQRS(RegistroRespuestaDTO registroRespuestaDTO) throws Exception;
    void cambiarEstadoPQRS(int codigoPQRS, EstadoPQRS estadoPQRS) throws Exception;


    //para poder atualizar el medico
    DetalleMedicoDTO obtenerMedico(int codigo) throws Exception;
}
