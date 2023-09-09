package com.uniquindio.edu.clinicaX.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Inheritance(strategy = InheritanceType.JOINED)
public class Usuario extends Cuenta{
    private Ciudad ciudad;
    private String cedula;
    private String nombre;
    private String telefono;
    private String urlFoto;
}
