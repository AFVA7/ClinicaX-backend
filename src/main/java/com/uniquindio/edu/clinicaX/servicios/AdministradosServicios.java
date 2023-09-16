package com.uniquindio.edu.clinicaX.servicios;

import com.uniquindio.edu.clinicaX.dto.*;

import java.util.List;

public interface AdministradosServicios {
    //admin puede gestionar médicos

    String crearMedico(MedicoDTO medico);
    String acualizarMedico(int codigo, MedicoDTO medico);
    String eliminarMedico(int codigo);
    List<MedicoDTOAdmin> listarMedicos();
    List<PQRDTOAdmin>ListarPQRS();
    String responderPQR(int codigo);
    infoPQRDTO verDetellaesPQR(int detalle);
    List<CitaDTOAdmin>listarCitas();
    //para poder atualizar el medico
    InfoMedicoDTO obtenerMedico(int codigo);
}
