package co.edu.uniquindio.clinicaX.servicios.impl;

import co.edu.uniquindio.clinicaX.dto.RegistroMensajeDTO;
import co.edu.uniquindio.clinicaX.dto.pqrs.DetallePQRSDTO;
import co.edu.uniquindio.clinicaX.dto.pqrs.ItemPQRSDTO;
import co.edu.uniquindio.clinicaX.dto.RegistroRespuestaDTO;
import co.edu.uniquindio.clinicaX.dto.RespuestaDTO;
import co.edu.uniquindio.clinicaX.dto.pqrs.RegistroPQRDTO;
import co.edu.uniquindio.clinicaX.model.*;
import co.edu.uniquindio.clinicaX.model.enums.EstadoPQRS;
import co.edu.uniquindio.clinicaX.repositorios.CitaRepo;
import co.edu.uniquindio.clinicaX.repositorios.MensajeRepo;
import co.edu.uniquindio.clinicaX.repositorios.PQRSRepo;
import co.edu.uniquindio.clinicaX.servicios.interfaces.PQRServicio;
import co.edu.uniquindio.clinicaX.servicios.validaciones.pqrs.PacienteTresPQRS;
import jakarta.transaction.Transactional;
import jakarta.validation.ValidationException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class PQRServicioImpl implements PQRServicio {
    private final PQRSRepo pqrsRepo;
    private final CitaRepo citaRepo;
    private final PacienteTresPQRS validacion;
    private final MensajeRepo mensajeRepo;
    private final MensajeServicioImpl mensajeServicios;

    @Override
    public int crearPQRS(RegistroPQRDTO datos) {
        //valida que el paciente no tenga más de 3 PQRS en proceso o nuevas
        validacion.validarTresPQRS(datos);
        //obtener la cita
        Cita cita = validarCita(datos.codigoCita());

        Pqrs pqrs = pqrsRepo.save(new Pqrs(datos, cita));
        //se crea el primer msj de la lista de msjs de la pqrs
        int codigoMensaje = mensajeServicios.crear(new RegistroMensajeDTO(datos, pqrs));
        //verificar que se crea el msj
        Mensaje mensaje = verificarMensaje(codigoMensaje);
        pqrs.getMensajes().add(mensaje);
        System.out.println("Pqrs "+pqrs.getCodigo()+" creada");
        return pqrs.getCodigo();
    }
    @Override
    public void cambiarEstadoPQRS(int codigoPQRS, EstadoPQRS estadoPQRS) {
        //validar que la pqrs exista
        Pqrs pqrs = validarPqrs(codigoPQRS);
        pqrs.setEstado(estadoPQRS);
    }
    @Override
    public List<ItemPQRSDTO> listarPQRS() {
        List<Pqrs> listaPqrs = pqrsRepo.findAll();
        if(listaPqrs.isEmpty()){
            throw new ValidationException("No existen pqrs creadas");
        }

        return listaPqrs.stream()
                .map(ItemPQRSDTO::new)
                .toList();
    }
    @Override
    public int responderPQRS(RegistroRespuestaDTO datos) {
        return mensajeServicios.responder(datos);
    }
    @Override
    public DetallePQRSDTO verDetallePQRS(int codigo) {
        //validamos que exista la pqrs
        Pqrs pqrs = validarPqrs(codigo);
        List<Mensaje> mensajes = mensajeRepo.findAllByPqrsCodigo(codigo);
        return new DetallePQRSDTO(pqrs, convertirRespuestasDTO(mensajes));
    }
    private List<RespuestaDTO> convertirRespuestasDTO(List<Mensaje> mensajes) {
        return mensajes.stream().map(RespuestaDTO::new).toList();
    }
    public Pqrs obtener(int codigo){
        return validarPqrs(codigo);
    }
    private Cita validarCita(int codigo) {
        Optional<Cita> opcional = citaRepo.findById(codigo);
        if (opcional.isEmpty()) {
            throw new ValidationException("No existe una CITA con el código " + codigo);
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
    private Mensaje verificarMensaje(int codigo) {
        Optional<Mensaje> opcional = mensajeRepo.findById(codigo);
        if (opcional.isEmpty()) {
            throw new ValidationException("No se pudo crear el msj para la pqr");
        }
        return opcional.get();
    }
}
