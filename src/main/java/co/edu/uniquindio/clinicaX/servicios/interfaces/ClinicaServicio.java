package co.edu.uniquindio.clinicaX.servicios.interfaces;

import co.edu.uniquindio.clinicaX.dto.admin.ItemMedicoDto;
import co.edu.uniquindio.clinicaX.model.enums.*;

import java.util.List;

public interface ClinicaServicio {
    List<Ciudad> listarCiudades();
    List<Especialidad> listarEspecialeidades();
    List<TipoSangre> listarTipoSangre();
    List<Eps> listarEps();
    List<EstadoCita> listarEstadoCita();
    List<EstadoPQRS> listarEstadoPQRS();
    List<ItemMedicoDto> listarMedicos() throws Exception;
}
