package co.edu.uniquindio.clinicaX.servicios.impl;

import co.edu.uniquindio.clinicaX.dto.pqrs.DetallePQRSDTO;
import co.edu.uniquindio.clinicaX.dto.pqrs.ItemPQRSDTO;
import co.edu.uniquindio.clinicaX.dto.RegistroRespuestaDTO;
import co.edu.uniquindio.clinicaX.dto.RespuestaDTO;
import co.edu.uniquindio.clinicaX.dto.pqrs.RegistroPQRDTO;
import co.edu.uniquindio.clinicaX.model.*;
import co.edu.uniquindio.clinicaX.model.enums.EstadoPQRS;
import co.edu.uniquindio.clinicaX.repositorios.CitaRepo;
import co.edu.uniquindio.clinicaX.repositorios.CuentaRepo;
import co.edu.uniquindio.clinicaX.repositorios.MensajeRepo;
import co.edu.uniquindio.clinicaX.repositorios.PQRSRepo;
import co.edu.uniquindio.clinicaX.servicios.interfaces.PQRServicios;
import co.edu.uniquindio.clinicaX.servicios.validaciones.pqrs.PacienteTresPQRS;
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
public class PQRServicioImpl implements PQRServicios {
    private final PQRSRepo pqrsRepo;
    private final CitaRepo citaRepo;
    private final PacienteTresPQRS validacion;
    private final MensajeRepo mensajeRepo;
    private final MensajeServiciosImpl mensajeServicios;
    @Override
    public int crearPQRS(RegistroPQRDTO datos) {
        validacion.validar(datos);
        Optional<Cita> opcional = citaRepo.findById(datos.codigoCita());
        if (opcional.isEmpty()){
            throw new ValidationException("No existe una CITA con el código "+datos.codigoCita());
        }
        Cita cita = opcional.get();
        Pqrs pqrs = pqrsRepo.save(new Pqrs(datos, cita));
        return pqrs.getCodigo();
    }

    @Override
    public void cambiarEstadoPQRS(int codigoPQRS, EstadoPQRS estadoPQRS) {
        Optional<Pqrs> opcional = pqrsRepo.findById(codigoPQRS);
        if (opcional.isEmpty()) {
            throw new ValidationException("No existe un PQRS con el código "+codigoPQRS);
        }
        Pqrs pqrs = opcional.get();
        pqrs.setEstado(estadoPQRS);
    }

    @Override
    public List<ItemPQRSDTO> listarPQRS() {
        List<Pqrs> listaPqrs = pqrsRepo.findAll();
        return listaPqrs.stream()
                .map(ItemPQRSDTO::new)
                .toList();//a partir de java 16
    }

    @Override
    public int responderPQRS(RegistroRespuestaDTO registroRespuestaDTO) {
        return mensajeServicios.crear(registroRespuestaDTO);
    }

    @Override
    public DetallePQRSDTO verDetallePQRS(int codigo) {
        Optional<Pqrs> opcional = pqrsRepo.findById(codigo);
        if (opcional.isEmpty()) {
            throw new ValidationException("No existe una PQRS con el código "+codigo);
        }
        Pqrs pqrs = opcional.get();
        List<Mensaje> mensajes = mensajeRepo.findAllByPqrsCodigo(codigo);

        return new DetallePQRSDTO(pqrs,convertirRespuestasDTO(mensajes));
    }
    private List<RespuestaDTO> convertirRespuestasDTO(List<Mensaje> mensajes) {
        return mensajes.stream().map(RespuestaDTO::new).toList();
    }


}
