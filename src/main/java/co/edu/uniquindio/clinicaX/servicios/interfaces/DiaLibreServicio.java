package co.edu.uniquindio.clinicaX.servicios.interfaces;

import co.edu.uniquindio.clinicaX.dto.medico.DiaLibreDTO;

import java.util.List;

public interface DiaLibreServicio {
    int crear(DiaLibreDTO diaLibreDTO);
    List<DiaLibreDTO> listar();
    DiaLibreDTO update(DiaLibreDTO datos);
    String eliminar(int codigo);
    DiaLibreDTO obtener(int Codigo);
}
