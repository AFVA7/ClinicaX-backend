package co.edu.uniquindio.clinicaX.dto.medico;

import co.edu.uniquindio.clinicaX.model.DiaLibre;

import java.time.LocalDate;

public record DiaLibreDTO(
        Integer codigo,
        int codigoMedico,
        LocalDate fecha
) {
    public DiaLibreDTO(DiaLibre diaLibre) {
        this(
                diaLibre.getCodigo(),
                diaLibre.getMedico().getCodigo(),
                diaLibre.getDia()
        );
    }
}
