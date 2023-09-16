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
    private Ciudad ciudad;
    @Column(nullable = false, length = 10, unique = true)
    private String cedula;
    @Column(nullable = false, length = 20)
    private String nombre;
    private String telefono;
    @Lob

    private String urlFoto;
}
