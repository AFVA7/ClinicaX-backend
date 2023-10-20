package co.edu.uniquindio.clinicaX.model;

import co.edu.uniquindio.clinicaX.dto.RegistroRespuestaDTO;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Inheritance(strategy = InheritanceType.JOINED)
public class Mensaje implements Serializable {
    @Id
    @EqualsAndHashCode.Include
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int codigo;

    @Column(nullable = false)
    private LocalDateTime fecha;

    private String tipo;

    @Lob
    private String contenido;

    @OneToOne(fetch = FetchType.LAZY)
    private Mensaje mensaje;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = false)
    private Cuenta cuenta;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = false)
    private Pqrs pqrs;

    public Mensaje(RegistroRespuestaDTO datos, LocalDateTime fecha, Pqrs pqrs, /*Mensaje mensaje,*/ Cuenta cuenta) {
        this.setFecha(fecha);
        this.setContenido(datos.mensaje());
        this.setCuenta(cuenta);
        this.setPqrs(pqrs);
    }
}
