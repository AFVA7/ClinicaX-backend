package co.edu.uniquindio.clinicaX.dto.paciente;

import co.edu.uniquindio.clinicaX.model.Paciente;

import java.time.LocalDate;

public record PacienteRespuestaDTO(
        int codigo,
        String cedula,
        String nombre,
        String telefono,
        String correo,
        LocalDate fechaNacimiento,
        String urlFoto,
        //las enumeraciones se pueden tomar literalmente o se puede hacer uso de
        //int, ya que cada valor que tiene una enumeración tiene un índice (número entero).
        int ciudad,
        int eps,
        int tipoSangre,
        String alergias

) {
    public PacienteRespuestaDTO(Paciente paciente){
        this(
                paciente.getCodigo(),
                paciente.getCedula(),
                paciente.getNombre(),
                paciente.getTelefono(),
                paciente.getCorreo(),
                paciente.getFechaNacimiento(),
                paciente.getUrlFoto(),
                paciente.getCiudad().ordinal(),//accediendo al índice de la instancia del enum, ;apea ciudad a su índice
                paciente.getEps().ordinal(),
                paciente.getTipoSangre().ordinal(),
                paciente.getAlergias()
        );
    }
}
