package com.uniquindio.edu.clinicaX.servicios.interfaces;

import com.uniquindio.edu.clinicaX.dto.*;
import com.uniquindio.edu.clinicaX.dto.admin.DetalleMedicoDTO;
import com.uniquindio.edu.clinicaX.dto.admin.ItemCitaAdminDTO;
import com.uniquindio.edu.clinicaX.dto.admin.ItemMedicoDto;
import com.uniquindio.edu.clinicaX.dto.admin.RegistroMedicoDTO;
import com.uniquindio.edu.clinicaX.model.EstadoPQRS;

import java.util.List;

public interface AdministradorServicios {
    //admin puede gestionar médicos

    String crearMedico(RegistroMedicoDTO medico) throws Exception;
    String acualizarMedico(DetalleMedicoDTO medico) throws Exception;
    String eliminarMedico(int codigo) throws Exception;
    List<ItemMedicoDto> listarMedicos() throws Exception;
    List<ItemPQRSDTO>ListarPQRS() throws Exception;
    DetallePQRSDTO verDetellaesPQRS(int codigo) throws Exception;
    List<ItemCitaAdminDTO>listarCitas()throws Exception;


    int responderPQRS(RegistroRespuestaDTO registroRespuestaDTO) throws Exception;
    void cambiarEstadoPQRS(int codigoPQRS, EstadoPQRS estadoPQRS) throws Exception;
    //para poder atualizar el medico
    DetalleMedicoDTO obtenerMedico(int codigo) throws Exception;
}
