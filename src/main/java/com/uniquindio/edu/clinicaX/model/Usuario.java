package com.uniquindio.edu.clinicaX.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Inheritance(strategy = InheritanceType.JOINED)
public class Usuario extends Cuenta{
    @Column(nullable = false)
    private Ciudad ciudad;
    @Column(nullable = false, length = 10, unique = true)
    private String cedula;
    @Column(nullable = false, length = 200)
    private String nombre;
    @Column(nullable = false, length = 20)
    private String telefono;
    @Lob
    @Column(nullable = false)
    private String urlFoto;
    @Column(nullable = false)
    private EstadoUsuario estado = EstadoUsuario.ACTIVO;
}
