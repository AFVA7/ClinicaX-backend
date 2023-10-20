package co.edu.uniquindio.clinicaX.model;

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

    public DiaLibre(DiaLibreDTO diaLibreDTO, Medico medico){
        this.setDia(diaLibreDTO.fecha());
        this.setMedico(medico);
    }

    public void actualizar(DiaLibreDTO datos, Medico medico) {
        this.setDia(datos.fecha());
        this.setMedico(medico);
    }
}
