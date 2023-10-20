package co.edu.uniquindio.clinicaX.servicios.impl;

import co.edu.uniquindio.clinicaX.dto.cita.*;
import co.edu.uniquindio.clinicaX.dto.medico.RegistroAtencionDTO;
import co.edu.uniquindio.clinicaX.dto.paciente.FiltroBusquedaDTO;
import co.edu.uniquindio.clinicaX.infra.errors.ValidacionDeIntegridadE;
import co.edu.uniquindio.clinicaX.model.Atencion;
import co.edu.uniquindio.clinicaX.model.Cita;
import co.edu.uniquindio.clinicaX.model.Medico;
import co.edu.uniquindio.clinicaX.model.Paciente;
import co.edu.uniquindio.clinicaX.model.enums.EstadoCita;
import co.edu.uniquindio.clinicaX.repositorios.AtencionRepo;
import co.edu.uniquindio.clinicaX.repositorios.CitaRepo;
import co.edu.uniquindio.clinicaX.repositorios.MedicoRepo;
import co.edu.uniquindio.clinicaX.repositorios.PacienteRepo;
import co.edu.uniquindio.clinicaX.servicios.interfaces.CitaServicios;
import co.edu.uniquindio.clinicaX.servicios.validaciones.agendar.ValidadorDeCitas;
import co.edu.uniquindio.clinicaX.servicios.validaciones.cancelar.ValidadorCancelamientoCitas;
import jakarta.transaction.Transactional;
import jakarta.validation.ValidationException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
@Transactional
public class CitaServiciosImpl implements CitaServicios {
    private final CitaRepo citaRepo;
    private final PacienteRepo pacienteRepo;
    private final MedicoRepo medicoRepo;
    private final AtencionRepo atencionRepo;
    private final AtencionServicioImpl atencionServicio;
    @Autowired
    List<ValidadorDeCitas> validadores;
    @Autowired
    List<ValidadorCancelamientoCitas> validadoresCancelamiento;
    @Override
    public DetalleCitaDTO agendarCita(AgendarCitaDTO datos) {
        Optional<Paciente> opcional = pacienteRepo.findById(datos.idPaciente());
        if (opcional.isEmpty()){
                throw new ValidacionDeIntegridadE("Este id para el paciente no fue encontrado");
        }
        if (datos.idMedico()!=null && !medicoRepo.existsById(datos.idMedico())){
            throw new ValidacionDeIntegridadE("Este id para el médico no fue encontrado");
        }
        //validaciones
        validadores.forEach(v->v.validar(datos));
        var paciente = opcional.get();
        var medico = seleccionarMedico(datos);
        if(medico==null){
            throw new ValidacionDeIntegridadE("No existen médicos disponibles para este " +
                    "horario y especialidad");
        }
        var cita = new Cita(LocalDateTime.now(), datos.fecha(), datos.motivo(), EstadoCita.PROGRAMADA, medico, paciente);
        citaRepo.save(cita);
        return new DetalleCitaDTO(cita);
        /*toma de decisiones, comunicación, resolución de porblemas, coordinación*/
    }

    private Medico seleccionarMedico(AgendarCitaDTO datos) {

        if (datos.idMedico()!=null){
            return medicoRepo.getReferenceById(datos.idMedico());
        }
        if (datos.especialidad()==null){
            throw new ValidacionDeIntegridadE("Debe seleccionar una especialidad para el médico");
        }
        return medicoRepo.seleccionaMedicoConEspecialidadEnFecha(datos.especialidad(),
                datos.fecha());
    }

    @Override
    public void cancelarCita(CancelamientoCitaDTO datos) {
        if (!citaRepo.existsById(datos.idCita())){
            throw new ValidacionDeIntegridadE("Id de la cita do existe!");
        }
        validadoresCancelamiento.forEach(v->v.validar(datos));
        var cita = citaRepo.getReferenceById(datos.idCita());
        cita.cancelar(datos.motivo());
    }

    @Override
    public List<ItemCitaDTO> listarCitas() {
        List<Cita> citas = citaRepo.findAll();
        if(citas.isEmpty()){
            throw new ValidationException("No existen citas creadas");
        }
        return citas.stream()
                .map(ItemCitaDTO::new)
                .toList();
    }

    @Override
    public List<ItemCitaDTO> listarHistorialPaciente(int codigoPaciente) {
        List<Cita> citas = citaRepo.findAllByPacienteCodigo(codigoPaciente);
        if(citas.isEmpty()){
            throw new ValidationException("No existe historial de citas para el paciente "+codigoPaciente);
        }
        return citas.stream()
                .map(ItemCitaDTO::new)
                .toList();

    }

    @Override
    public DetalleCitaDTO verDetalleCita(int codigoCita) {
        Optional<Cita> opcionalCita = citaRepo.findById(codigoCita);
        if( opcionalCita.isEmpty() ){
            throw new ValidacionDeIntegridadE("No existe una cita con el código "+codigoCita);
        }
        Cita cita = opcionalCita.get();
        Optional<Cita> opcionalAtencion = citaRepo.findById(codigoCita);
        if(opcionalAtencion.isEmpty() ){
            throw new ValidacionDeIntegridadE("Aun no hay atención para la cita con código "+codigoCita);
        }
        return new DetalleCitaDTO(cita);
    }

    @Override
    public List<ItemCitaDTO> filtrarCitasPorMedico(int idMedico) {
        List<Cita> citas = citaRepo.findAllByMedicoCodigo(idMedico);
        if(citas.isEmpty()){
            throw new ValidationException("No existen citas asociandas al medico "+idMedico);
        }
        return citas.stream().map(ItemCitaDTO::new).toList();
    }

    @Override
    public List<ItemCitaDTO> filtrarCitasPorFecha(FiltroBusquedaDTO filtroBusquedaDTO) {
        int codigoPaciente = filtroBusquedaDTO.codigoPaciente();
        LocalDateTime fechaInicio = filtroBusquedaDTO.fechaInicio();
        LocalDateTime fechaFin = filtroBusquedaDTO.fechaFin();
        List<Cita> citas = citaRepo.findCitaByPacienteCodigoAndFechaCitaBetween(codigoPaciente, fechaInicio, fechaFin);
        if(citas.isEmpty()){
            throw new ValidationException("No existen citas en esa fecha");
        }
        return citas.stream().map(ItemCitaDTO::new).toList();
    }

    @Override
    public List<ItemCitaDTO> filtrarCitaspendientesMedico(int codigoMedico) {
        List<Cita> citas = citaRepo.findByEstadoAndMedicoCodigo(EstadoCita.PROGRAMADA, codigoMedico);
        if (citas.isEmpty()) {
            throw new ValidationException("No hay citas pendientes");
        }
        return citas.stream().map(ItemCitaDTO::new).toList();
    }
    @Override
    public List<ItemCitaDTO> listarCitasPendientesPaciente(int codigoPaciente) {
        List<Cita> citas = citaRepo.findByEstadoAndPacienteCodigo(EstadoCita.PROGRAMADA, codigoPaciente);
        if (citas.isEmpty()) {
            throw new ValidationException("No hay citas pendientes");
        }
        return citas.stream().map(ItemCitaDTO::new).toList();

    }

    @Override
    public int atenderCita(RegistroAtencionDTO datos) {
        return atencionServicio.crear(datos);
    }

    @Override
    public List<ItemAtencionDTO> listarTodasCitasMedico(int codigoMedico) {
        return atencionServicio.listar(codigoMedico);
    }
}
