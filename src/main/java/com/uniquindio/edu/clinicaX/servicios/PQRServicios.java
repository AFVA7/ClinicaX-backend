package com.uniquindio.edu.clinicaX.servicios;

public interface PQRServicios {
    //paciente
    //No se podrá tener activa o en progreso más de tres PQRS al mismo tiempo por paciente.
    //validar: quien está creando la PQR, mirar que el que la está creando no tenga más de 3 activas
    void crearPQR();
    /*Al crear la petición, automáticamente pasa a un estado de activa.*/
    void cambiarEstadoPQR();
    void responderPQR();
    //en todo momento el paciente podrá ver el historial de las peticiones
    void listarPQRSPaciente();
    /*Mientras las peticiones se encuentren en progreso,
    el paciente podrá dar respuesta a lo que haya dicho el
    administrador para ampliar los detalles.
     */
    void verDetallePQR();
    //medico
    //admin
}
