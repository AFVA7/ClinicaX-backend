package co.edu.uniquindio.clinicaX.dto.medico;

import co.edu.uniquindio.clinicaX.model.DiaLibre;

import java.time.LocalDate;

public record DetalleDiaLibreDTO(
        Integer codigo,
        Integer codigoMedico,
        LocalDate fecha,
        String motivo
) {
    public DetalleDiaLibreDTO(DiaLibre diaLibre) {
        this(
                diaLibre.getCodigo(),
                diaLibre.getMedico().getCodigo(),
                diaLibre.getDia(),
                diaLibre.getMotivo()
        );
    }

}
