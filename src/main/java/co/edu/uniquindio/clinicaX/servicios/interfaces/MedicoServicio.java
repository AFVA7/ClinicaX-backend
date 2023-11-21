package co.edu.uniquindio.clinicaX.servicios.interfaces;

import co.edu.uniquindio.clinicaX.dto.cita.DetalleAtencionMedicaDTO;
import co.edu.uniquindio.clinicaX.dto.admin.DetalleMedicoDTO;
import co.edu.uniquindio.clinicaX.dto.cita.ItemCitaDTO;
import co.edu.uniquindio.clinicaX.dto.admin.ItemMedicoDto;
import co.edu.uniquindio.clinicaX.dto.admin.RegistroMedicoDTO;
import co.edu.uniquindio.clinicaX.dto.medico.*;
import co.edu.uniquindio.clinicaX.model.enums.Especialidad;

import java.util.List;

public interface MedicoServicio {
    int crearMedico(RegistroMedicoDTO medicoDTO);
    String acualizarMedico(DetalleMedicoDTO medicoDTO) throws Exception;
    String eliminarMedico(int codigo) throws Exception;
    DetalleMedicoDTO obtenerMedico(int codigo) throws Exception;
    List<ItemMedicoDto> listarMedicos() throws Exception;
    List<ItemCitaDTO> listarCitasPendientes(int codigoMedico);
    int atenderCita(RegistroAtencionDTO registroAtencionDTO) throws Exception;
    List<ItemCitaDTO> listarHistorialPaciente(int codigoPaciente) throws Exception;//historial medico
    int agendarDiaLibre(DiaLibreDTO diaLibreDTO) throws Exception;
    List<ItemCitaDTO> listarCitasRealizadasMedico(int codigoMedico) throws Exception; // listado de consultas
    DetalleAtencionMedicaDTO verDetalleAtencion(int codigoCita) throws Exception;


    List<ItemMedicoDto> obtenerMedicosPorEspecialidad(Especialidad especialidad);
}
