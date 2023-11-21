package co.edu.uniquindio.clinicaX.dto.admin;
import co.edu.uniquindio.clinicaX.model.*;
import co.edu.uniquindio.clinicaX.model.enums.Especialidad;

import java.util.List;

public record ItemMedicoDto(
        Integer codigo,
        String nombre,
        String urlFoto,
        Especialidad especialidad,
        List<HorarioDTO> horarios
) {
    public ItemMedicoDto(Medico medico) {
        this(
                medico.getCodigo(),
                medico.getNombre(),
                medico.getUrlFoto(),
                medico.getEspecialidad(),
                medico.getHorarios().stream().map(HorarioDTO::new).toList()
        );
    }
}
