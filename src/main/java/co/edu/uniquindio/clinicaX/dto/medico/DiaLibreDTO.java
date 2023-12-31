package co.edu.uniquindio.clinicaX.dto.medico;

import co.edu.uniquindio.clinicaX.model.DiaLibre;

import java.time.LocalDate;

public record DiaLibreDTO(
        Integer codigoMedico,
        LocalDate fecha,
        String motivo
) {
    public DiaLibreDTO(DiaLibre diaLibre) {
        this(
                diaLibre.getMedico().getCodigo(),
                diaLibre.getDia(),
                diaLibre.getMotivo()
        );
    }
}
