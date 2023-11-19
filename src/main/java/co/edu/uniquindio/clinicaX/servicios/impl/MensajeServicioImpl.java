package co.edu.uniquindio.clinicaX.servicios.impl;

import co.edu.uniquindio.clinicaX.dto.RegistroMensajeDTO;
import co.edu.uniquindio.clinicaX.dto.RegistroRespuestaDTO;
import co.edu.uniquindio.clinicaX.dto.RespuestaDTO;
import co.edu.uniquindio.clinicaX.dto.pqrs.ItemPQRSDTO;
import co.edu.uniquindio.clinicaX.infra.errors.ValidacionDeIntegridadE;
import co.edu.uniquindio.clinicaX.model.Cuenta;
import co.edu.uniquindio.clinicaX.model.Mensaje;
import co.edu.uniquindio.clinicaX.model.Pqrs;
import co.edu.uniquindio.clinicaX.model.enums.EstadoPQRS;
import co.edu.uniquindio.clinicaX.repositorios.CuentaRepo;
import co.edu.uniquindio.clinicaX.repositorios.MensajeRepo;
import co.edu.uniquindio.clinicaX.repositorios.PQRSRepo;
import co.edu.uniquindio.clinicaX.servicios.interfaces.MensajeServicio;
import jakarta.transaction.Transactional;
import jakarta.validation.ValidationException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class MensajeServicioImpl implements MensajeServicio {
    private final MensajeRepo mensajeRepo;
    private final PQRSRepo pqrsRepo;
    private final CuentaRepo cuentaRepo;

    @Override
    public int crear(RegistroMensajeDTO datos) {
        //Obtener el PQRS
        Pqrs pqrs = validarPqrs(datos.codigoPQRS());
        //Obtener la cuenta
        Cuenta cuenta = validarCuenta(datos.codigoCuenta());

        Mensaje mensaje = mensajeRepo.save(new Mensaje(
                datos,
                LocalDateTime.now(),
                pqrs,
                cuenta));

        System.out.println("Mensaje " + mensaje.getCodigo() + " creado");
        return mensaje.getCodigo();
    }

    public int responder(RegistroRespuestaDTO datos) {
        //Obtener el PQRS
        Pqrs pqrs = validarPqrs(datos.codigoPQRS());
        //Obtener la cuenta
        Cuenta cuenta = validarCuenta(datos.codigoCuenta());
        //obtener el mensaje
        Mensaje mensaje = validarMensaje(datos.codigoMensaje());

        Mensaje respuesta = mensajeRepo.save(new Mensaje(
                datos,
                LocalDateTime.now(),
                pqrs,
                mensaje,
                cuenta
        ));
        pqrs.getMensajes().add(respuesta);
        pqrs.setEstado(EstadoPQRS.ENPROCESO);
        System.out.println("Respuesta " + respuesta.getCodigo() + " creada");
        return respuesta.getCodigo();
    }
    @Override
    public List<RespuestaDTO> listar(int codigo) {
        List<Mensaje> listaMensajes = mensajeRepo.findAllByPqrsCodigo(codigo);
        if(listaMensajes.isEmpty()){
            throw new ValidationException("No existen msjs asociados");
        }

        return listaMensajes.stream()
                .map(RespuestaDTO::new)
                .toList();
    }

    @Override
    public RespuestaDTO update(RegistroMensajeDTO datos) {
        return null;
    }

    @Override
    public int eliminar(int codigo) {
        return 0;
    }

    @Override
    public RespuestaDTO obtener(int codigo) {
        Mensaje mensaje = validarMensaje(codigo);
        return new RespuestaDTO(mensaje);
    }

    private Mensaje validarMensaje(int codigo) {
        Optional<Mensaje> opcional = mensajeRepo.findById(codigo);
        if (opcional.isEmpty()) {
            throw new ValidacionDeIntegridadE("No existe un mensaje con el código " + codigo);
        }
        return opcional.get();
    }
    private Cuenta validarCuenta(int codigo) {
        Optional<Cuenta> opcional = cuentaRepo.findById(codigo);
        if (opcional.isEmpty()) {
            throw new ValidationException("No existe una cuenta con el código " + codigo);
        }
        return opcional.get();
    }
    private Pqrs validarPqrs(int codigo) {
        Optional<Pqrs> opcional = pqrsRepo.findById(codigo);
        if (opcional.isEmpty()) {
            throw new ValidationException("No existe un PQRS con el código " + codigo);
        }
        return opcional.get();
    }



}
