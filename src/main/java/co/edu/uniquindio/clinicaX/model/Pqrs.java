package co.edu.uniquindio.clinicaX.model;

import co.edu.uniquindio.clinicaX.dto.pqrs.RegistroPQRDTO;
import co.edu.uniquindio.clinicaX.model.enums.EstadoPQRS;
import co.edu.uniquindio.clinicaX.model.enums.TipoPQRS;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Pqrs implements Serializable {
    @Id
    @EqualsAndHashCode.Include
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int codigo;

    @Column(nullable = false)
    private LocalDateTime fechaCreacion;

    private TipoPQRS tipo;

    @Lob
    private String motivo;

    @Column(nullable = false)
    private EstadoPQRS estado;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = false)
    private Cita cita;

    @OneToMany(mappedBy = "pqrs")
    private List<Mensaje> mensajes;

    public Pqrs(RegistroPQRDTO datos, Cita cita){
        this.setFechaCreacion(LocalDateTime.now());
        this.setTipo(datos.tipoPQR());
        this.setMotivo(datos.motivo());
        this.setEstado(EstadoPQRS.NUEVO);
        this.setCita(cita);
        this.mensajes = new ArrayList<>();
    }
}
