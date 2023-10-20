package co.edu.uniquindio.clinicaX.servicios.interfaces;

import co.edu.uniquindio.clinicaX.dto.cita.DetalleAtencionMedicaDTO;
import co.edu.uniquindio.clinicaX.dto.cita.ItemAtencionDTO;
import co.edu.uniquindio.clinicaX.dto.medico.RegistroAtencionDTO;

import java.util.List;

public interface AtencionServicios {

     int crear(RegistroAtencionDTO registroAtencionDTO);
     List<ItemAtencionDTO> listar(int CodigoMedico);
     DetalleAtencionMedicaDTO update(DetalleAtencionMedicaDTO datos);
     String eliminar(int codigo);
     DetalleAtencionMedicaDTO verDetalle(int codigo);
}
