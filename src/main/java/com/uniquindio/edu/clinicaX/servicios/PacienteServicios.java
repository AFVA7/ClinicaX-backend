package com.uniquindio.edu.clinicaX.servicios;

public interface PacienteServicios {
    //servicio de registro

    //Un paciente puede registrarse
    void registrarse();

    //Un paciente ´puede editar su info personal
    void editarPerfil();

    //Un paciente ´puede recuperar su contraseña
    void recuperarPasswd();

    void enviarLinkRecuperacion();

    void cambiarPassword();

    //Un paciente ´puede eliminar la cuenta
    void eliminarCuenta();




    //servicios de cita
    void agendarCita();

    void listarCitasPaciente();

    void filtrarCitasPorFecha();

    void filtrarCitasPorMedico();

    void verDetalleCita();



    //servicios de PQRS
    void crearPQRS();

    void listarPQRSPaciente();

    void responderPQRS();


}
