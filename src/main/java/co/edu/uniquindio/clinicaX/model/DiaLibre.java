package co.edu.uniquindio.clinicaX.model;

import co.edu.uniquindio.clinicaX.dto.medico.DetalleDiaLibreDTO;
import co.edu.uniquindio.clinicaX.dto.medico.DiaLibreDTO;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDate;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class DiaLibre implements Serializable {

    @Id
    @EqualsAndHashCode.Include
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int codigo;

    @Column(nullable = false)
    private LocalDate dia;

    @ManyToOne(fetch = FetchType.LAZY)
    private Medico medico;

    public String motivo;
    public DiaLibre(DiaLibreDTO diaLibreDTO, Medico medico){
        this.setDia(diaLibreDTO.fecha());
        this.setMedico(medico);
        this.setMotivo(diaLibreDTO.motivo());
    }

    public void actualizar(DetalleDiaLibreDTO datos, Medico medico) {
        this.setDia(datos.fecha());
        this.setMedico(medico);
        this.setMotivo(datos.motivo());
    }
}
