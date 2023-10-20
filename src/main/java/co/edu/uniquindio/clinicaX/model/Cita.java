package co.edu.uniquindio.clinicaX.model;

import co.edu.uniquindio.clinicaX.model.enums.EstadoCita;
import co.edu.uniquindio.clinicaX.model.enums.MotivoCancelamiento;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Cita implements Serializable {
    @Id
    @EqualsAndHashCode.Include
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int codigo;

    @Column(nullable = false)
    private LocalDateTime fechaCreacion;

    @Column(nullable = false)
    private LocalDateTime fechaCita;

    @Lob
    private String motivo;

    @Lob
    private MotivoCancelamiento motivoCancelamiento;

    @Column(nullable = false)
    private EstadoCita estado;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = false)
    private Medico medico;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = false)
    private Paciente paciente;

    @OneToMany(mappedBy = "cita")
    private List<Pqrs> pqrs;

    @OneToOne(fetch = FetchType.LAZY, mappedBy = "cita")
    private Atencion atencion;

    public Cita(LocalDateTime fechaCreacion,LocalDateTime  fechaCita, String motivo, EstadoCita estdo, Medico medico, Paciente paciente) {
        this.fechaCreacion = fechaCreacion;
        this.fechaCita = fechaCita;
        this.motivo = motivo;
        this.estado = estdo;
        this.medico = medico;
        this.paciente = paciente;
    }

    public void cancelar(MotivoCancelamiento motivo) {
        this.motivoCancelamiento = motivo;
        this.setEstado(EstadoCita.CANCELADA);
    }
}
