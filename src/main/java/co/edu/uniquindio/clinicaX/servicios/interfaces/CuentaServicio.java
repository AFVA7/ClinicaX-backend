package co.edu.uniquindio.clinicaX.servicios.interfaces;

import co.edu.uniquindio.clinicaX.dto.NuevaPasswordDTO;
import co.edu.uniquindio.clinicaX.model.Cuenta;

public interface CuentaServicio {
    void enviarLinkRecuperacion(String email) throws Exception;
    public void cambiarPasswd(NuevaPasswordDTO nuevaPasswordDTO) throws Exception;
    public void recuperarPasswd();
    Cuenta obtenerCuentaCodigo(int codigoCuenta);

}
