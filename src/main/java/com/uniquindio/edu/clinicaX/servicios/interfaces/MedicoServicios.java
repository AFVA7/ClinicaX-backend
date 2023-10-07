package com.uniquindio.edu.clinicaX.servicios.interfaces;

import com.uniquindio.edu.clinicaX.dto.DetalleAtencionMedicaDTO;
import com.uniquindio.edu.clinicaX.dto.admin.ItemCitaAdminDTO;
import com.uniquindio.edu.clinicaX.dto.medico.DiaLibreDTO;
import com.uniquindio.edu.clinicaX.dto.medico.RegistroAtencionDTO;

import java.util.List;

public interface MedicoServicios {
    List<ItemCitaAdminDTO> listarCitasPendientes(int codigoMedico);
    int atenderCita(RegistroAtencionDTO registroAtencionDTO) throws Exception;
    List<ItemCitaAdminDTO> listarCitasPaciente(int codigoPaciente) throws Exception;//historial medico
    int agendarDiaLibre(DiaLibreDTO diaLibreDTO) throws Exception;
    List<ItemCitaAdminDTO> listarCitasRealizadasMedico(int codigoMedico) throws Exception; // listado de consultas
    DetalleAtencionMedicaDTO verDetalleAtencion(int codigoCita) throws Exception;


}
