package co.edu.uniquindio.clinicaX.infra.errors;

//Es el mismo ResourseNotFoundException
public class ValidacionDeIntegridadE extends RuntimeException {
    public ValidacionDeIntegridadE(String s) {
        super(s);
    }
}
