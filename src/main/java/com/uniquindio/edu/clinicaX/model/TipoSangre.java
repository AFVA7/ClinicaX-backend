package com.uniquindio.edu.clinicaX.model;

public enum TipoSangre {

    A_POSITIVO("A+"),
    A_NEGATIVO("A-");

    private String nombre;
    TipoSangre(String nombre){
        this.nombre = nombre;
    }

}
