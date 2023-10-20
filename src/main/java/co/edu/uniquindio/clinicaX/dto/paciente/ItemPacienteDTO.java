package co.edu.uniquindio.clinicaX.dto.paciente;

import co.edu.uniquindio.clinicaX.model.Paciente;
import co.edu.uniquindio.clinicaX.model.enums.Ciudad;

public record ItemPacienteDTO(
        int codigo,
        String cedula,
        String nombre,
        Ciudad ciudad
) {
    public ItemPacienteDTO(Paciente paciente){
        this(
                paciente.getCodigo(),
                paciente.getCedula(),
                paciente.getNombre(),
                paciente.getCiudad()
        );
    }
}
