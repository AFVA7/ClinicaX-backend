package co.edu.uniquindio.clinicaX.dto.admin;
import co.edu.uniquindio.clinicaX.model.*;
import co.edu.uniquindio.clinicaX.model.enums.Especialidad;

public record ItemMedicoDto(
        int codigo,
        String nombre,
        String cedula,
        String urlFoto,
        Especialidad especialidad
) {
    public ItemMedicoDto(Medico medico) {
        this(
                medico.getCodigo(),
                medico.getNombre(),
                medico.getCedula(),
                medico.getUrlFoto(),
                medico.getEspecialidad()
        );
    }
}
