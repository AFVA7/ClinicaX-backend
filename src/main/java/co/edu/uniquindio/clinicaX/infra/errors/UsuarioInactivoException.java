package co.edu.uniquindio.clinicaX.infra.errors;

public class UsuarioInactivoException extends RuntimeException {
    public UsuarioInactivoException(String s) {
        super(s);
    }
}
