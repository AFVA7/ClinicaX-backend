package co.edu.uniquindio.clinicaX.dto;

import co.edu.uniquindio.clinicaX.model.Cita;
import co.edu.uniquindio.clinicaX.model.Medico;

public record EmailDTO(
        String destinatario,
        String asunto,
        String cuerpo) {

    public EmailDTO(Cita cita, boolean esPaciente){
        this(
                esPaciente?cita.getPaciente().getCorreo() : cita.getMedico().getCorreo(),
                "Bienvenido a clinicaX",
                "Se ha creado una cita con su cuenta para la fecha " + cita.getFechaCita()
        );
    }

}
