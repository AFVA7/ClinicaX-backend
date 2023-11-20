package co.edu.uniquindio.clinicaX.servicios.interfaces;

import co.edu.uniquindio.clinicaX.dto.medico.DetalleDiaLibreDTO;
import co.edu.uniquindio.clinicaX.dto.medico.DiaLibreDTO;

import java.util.List;

public interface DiaLibreServicio {
    int crear(DiaLibreDTO diaLibreDTO);
    List<DetalleDiaLibreDTO> listar();
    DiaLibreDTO update(DetalleDiaLibreDTO datos);
    String eliminar(int codigo);
    DetalleDiaLibreDTO obtener(int Codigo);
}
