package com.uniquindio.edu.clinicaX.dto;

//contiene solo los datos que al Admin le interesa ver en listarMedicos
public record MedicoDTOAdmin(
        String urlFoto,
        String nombre,
        String cedula) {

}
