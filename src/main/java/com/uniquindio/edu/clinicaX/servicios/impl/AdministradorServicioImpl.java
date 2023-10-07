package com.uniquindio.edu.clinicaX.servicios.impl;

import com.uniquindio.edu.clinicaX.dto.DetallePQRSDTO;
import com.uniquindio.edu.clinicaX.dto.admin.*;
import com.uniquindio.edu.clinicaX.dto.ItemPQRSDTO;
import com.uniquindio.edu.clinicaX.dto.RegistroRespuestaDTO;
import com.uniquindio.edu.clinicaX.model.*;
import com.uniquindio.edu.clinicaX.repositorios.*;
import com.uniquindio.edu.clinicaX.servicios.interfaces.AdministradorServicios;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AdministradorServicioImpl implements AdministradorServicios {

    private final MedicoRepo medicoRepo;
    private final PQRSRepo pqrsRepo;
    private final CitaRepo citaRepo;
    private final MensajeRepo mensajeRepo;
    private final CuentaRepo cuentaRepo;
    private final HorarioRepo horarioRepo;

    @Override
    public String crearMedico(RegistroMedicoDTO medicoDTO) throws Exception {
        if (estaRepetidaCedula(medicoDTO.correo())){
            throw new Exception("La cédula "+medicoDTO.correo()+" ya está en uso");
        }
        if (estaRepetidoCorreo(medicoDTO.cedula())){
            throw new Exception("El correo "+medicoDTO.correo()+" ya está en uso");
        }
        Medico medico = new Medico();
        medico.registrar(medicoDTO);
        Medico medicoNuevo = medicoRepo.save(medico);
        asignarHorariosMedico(medicoNuevo, medicoDTO.horarios());
        return "Medico " + medicoNuevo.getCodigo() + " creado";
    }

    private boolean estaRepetidoCorreo(String correo) {
        return medicoRepo.findByCorreo(correo)!=null;
    }

    private boolean estaRepetidaCedula(String cedula) {
        return medicoRepo.findByCedula(cedula)!=null;
    }

    private void asignarHorariosMedico(Medico medicoNuevo, List<HorarioDTO> horarios) {
        for(HorarioDTO h : horarios){
            HorarioMedico hm = new HorarioMedico();
            hm.setDia(h.dia());
            hm.setHoraInicio(h.horaInicio());
            hm.setHoraFin(h.horaSalida());
            hm.setMedico(medicoNuevo);
            horarioRepo.save(hm);
        }
    }

    @Override
    public String acualizarMedico(DetalleMedicoDTO medicoDTO) throws Exception {
        Optional<Medico> opcional = medicoRepo.findById(medicoDTO.codigo());
        if (opcional.isEmpty()) {
            throw new Exception("No existe un médico con el código " + medicoDTO.codigo());
        }
        Medico buscado = opcional.get();
        buscado.actualizar(medicoDTO);
        medicoRepo.save(buscado);//si es necesario?

        return "Médico " + buscado.getCodigo() + " actualizado con éxito";
    }

    @Override
    public String eliminarMedico(int codigo) throws Exception {
        Optional<Medico> opcional = medicoRepo.findById(codigo);
        if (opcional.isEmpty()) {
            throw new Exception("No existe un médico con el código " + codigo);
        }
        Medico buscado = opcional.get();
        buscado.setEstado(EstadoUsuario.INACTIVO);
        medicoRepo.save(buscado);//Si es necesario? ya quedó seteado es valor
        return "Médico eliminado con éxito";
    }

    @Override
    public List<ItemMedicoDto> listarMedicos() throws Exception {
        List<Medico> medicos = medicoRepo.findAll();
        if (medicos.isEmpty()) {
            throw new Exception("No hay médicos registrados");
        }
        return medicos.stream()
                .filter(m -> m.getEstado() == EstadoUsuario.ACTIVO)
                .map(ItemMedicoDto::new)
                .collect(Collectors.toList());//a partir de java 8
    }

    @Override
    public List<ItemPQRSDTO> ListarPQRS() throws Exception {
        List<Pqrs> listaPqrs = pqrsRepo.findAll();
        return listaPqrs.stream()
                .map(ItemPQRSDTO::new)
                .toList();//a partir de java 16
    }

    @Override
    public DetallePQRSDTO verDetellaesPQRS(int codigo) throws Exception {
        Optional<Pqrs> opcional = pqrsRepo.findById(codigo);
        if (opcional.isEmpty()) {
            throw new Exception("El código " + codigo + " no está asociado a ningún PQRS");
        }
        Pqrs pqrs = opcional.get();
        return new DetallePQRSDTO(pqrs);
    }

    @Override
    public List<ItemCitaAdminDTO> listarCitas() {
        List<Cita> listaCitas = citaRepo.findAll();

        return listaCitas.stream()
                .map(ItemCitaAdminDTO::new)
                .toList();
    }

    @Override
    public int responderPQRS(RegistroRespuestaDTO registroRespuestaDTO) throws Exception {
        //Obtener el PQRS
        Optional<Pqrs> opcionalPqrs = pqrsRepo.findById(registroRespuestaDTO.codigoPQRS());

        if (opcionalPqrs.isEmpty()) {
            throw new Exception("El código " + registroRespuestaDTO.codigoPQRS() + " no está asociado a ningún PQRS");
        }

        //Obtener la cuenta
        Optional<Cuenta> opcionalCuenta = cuentaRepo.findById(registroRespuestaDTO.codigoCuenta());

        if (opcionalCuenta.isEmpty()) {
            throw new Exception("El código " + registroRespuestaDTO.codigoCuenta() + " no está asociado a ningún PQRS");
        }

        Mensaje mensaje = new Mensaje();
        mensaje.setFecha(LocalDateTime.now());
        mensaje.setContenido(registroRespuestaDTO.mensaje());
        mensaje.setPqrs(opcionalPqrs.get());
        mensaje.setCuenta(opcionalCuenta.get());

        return mensajeRepo.save(mensaje).getCodigo();
    }

    @Override
    public void cambiarEstadoPQRS(int codigoPQRS, EstadoPQRS estadoPQRS) throws Exception {
        Optional<Pqrs> opcional = pqrsRepo.findById(codigoPQRS);
        if (opcional.isEmpty()) {
            throw new Exception("El código " + codigoPQRS + " no está asociado a ningún PQRS");
        }
        Pqrs pqrs = opcional.get();
        pqrs.setEstado(estadoPQRS);
        pqrsRepo.save(pqrs);//Es necesario?
    }

    @Override
    public DetalleMedicoDTO obtenerMedico(int codigo) throws Exception {
        DetalleMedicoDTO detalleMedicoDTO;
        Optional<Medico> opcional = medicoRepo.findById(codigo);
        if (opcional.isEmpty()) {
            throw new Exception("No existe un médico con el código " + codigo);
        }
        Medico buscado = opcional.get();
        detalleMedicoDTO = new DetalleMedicoDTO(
                buscado
        );
        return detalleMedicoDTO;
    }
}
