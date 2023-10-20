package co.edu.uniquindio.clinicaX.model;

import co.edu.uniquindio.clinicaX.dto.admin.HorarioDTO;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class HorarioMedico implements Serializable {
    @Id
    @EqualsAndHashCode.Include
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int codigo;

    @Column(nullable = false)
    private String dia;

    @Column(nullable = false)
    private LocalTime horaInicio;

    @Column(nullable = false)
    private LocalTime horaFin;

    @ManyToOne(fetch = FetchType.LAZY)
    private Medico medico;

    public HorarioMedico(HorarioDTO h, Medico medico){
        this.setDia(h.dia());
        this.setHoraInicio(h.horaInicio());
        this.setHoraFin(h.horaSalida());
        this.setMedico(medico);
    }

}
