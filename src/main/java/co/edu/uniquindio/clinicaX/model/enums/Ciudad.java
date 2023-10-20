package co.edu.uniquindio.clinicaX.model.enums;

public enum Ciudad {
    BOGOTA("Bogota"),
    MEDELLIN("Medellin"),
    CARTAGENA("Cartagena"),
    CALI("Cali"),
    SANTAMARTA("Santamarta"),
    BARRANQUILLA("Barranquilla"),
    VILLAVICENCIO("Villavicencio"),
    BUCARAMANGA("Bucaramanga"),
    IBAGUE("Ibague"),
    ARMENIA("Armenia"),
    PEREIRA("Pereira"),
    MANIZALES("Manizales");
    private String nombre;

    Ciudad(String nombre) {
        this.nombre = nombre;
    }
}
