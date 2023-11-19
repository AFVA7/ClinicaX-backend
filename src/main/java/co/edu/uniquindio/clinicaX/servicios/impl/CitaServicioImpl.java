package co.edu.uniquindio.clinicaX.servicios.impl;

import co.edu.uniquindio.clinicaX.dto.EmailDTO;
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
import co.edu.uniquindio.clinicaX.servicios.interfaces.CitaServicio;
import co.edu.uniquindio.clinicaX.servicios.interfaces.EmailServicio;
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
public class CitaServicioImpl implements CitaServicio {
    private final CitaRepo citaRepo;
    private final PacienteRepo pacienteRepo;
    private final MedicoRepo medicoRepo;
    private final AtencionRepo atencionRepo;
    private final AtencionServicioImpl atencionServicio;
    private final EmailServicio emailServicios;
    @Autowired
    List<ValidadorDeCitas> validadores;
    @Autowired
    List<ValidadorCancelamientoCitas> validadoresCancelamiento;
    @Override
    public int agendarCita(AgendarCitaDTO datos) throws Exception {
        var paciente = validaPaciente(datos.idPaciente());
        if (datos.idMedico()!=null && !medicoRepo.existsById(datos.idMedico())){
            throw new ValidacionDeIntegridadE("Este id para el médico no fue encontrado");
        }
        //validaciones
        validadores.forEach(v->v.validar(datos));
        var medico = seleccionarMedico(datos);
        if(medico==null){
            throw new ValidacionDeIntegridadE("No existen médicos disponibles para este " +
                    "horario y especialidad");
        }
        var cita = new Cita(LocalDateTime.now(), datos.fecha(), datos.motivo(), EstadoCita.PROGRAMADA, medico, paciente);
        citaRepo.save(cita);
        //Enviar Email al Paciente
        EmailDTO emailDTOPaciente = new EmailDTO(cita, true);
        //Enviar Email al Medico
        EmailDTO emailDTOMedico = new EmailDTO(cita, false);
        emailServicios.enviarEmail(emailDTOPaciente);
        emailServicios.enviarEmail(emailDTOMedico);
        return cita.getCodigo();
        /*toma de decisiones, comunicación, resolución de porblemas, coordinación*/
    }

    public Medico seleccionarMedico(AgendarCitaDTO datos) {

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
    public List<ItemCitaDTO> listarTodasLasCitasDeUnPaciente(int codigo) {
        List<Cita> citas = citaRepo.findAllByPacienteCodigo(codigo);
        if(citas.isEmpty()){
            throw new ValidationException("No existen citas creadas para el paciente " + codigo);
        }
        return citas.stream()
                .map(ItemCitaDTO::new)
                .toList();
    }

    @Override
    public List<ItemCitaDTO> listarHistorialPaciente(int codigoPaciente) {
        List<Cita> citas = citaRepo.findHistorial(codigoPaciente);
        if(citas.isEmpty()){
            throw new ValidationException("No existe historial de citas para el paciente "+codigoPaciente);
        }
        return citas.stream()
                .map(ItemCitaDTO::new)
                .toList();

    }

    @Override
    public DetalleCitaDTO verDetalleCita(int codigoCita) {
        Cita cita = validaCita(codigoCita);
        //valida que exista una atención asociada a esta cita
        //validaAtencion(codigoCita);
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

    public Paciente validaPaciente(int codigo){
        Optional<Paciente> opcional = pacienteRepo.findById(codigo);
        if (opcional.isEmpty()){
            throw new ValidacionDeIntegridadE("Este id para el paciente no fue encontrado");
        }
        return opcional.get();
    }

    public Cita validaCita(int codigo){
        Optional<Cita> opcional = citaRepo.findById(codigo);
        if(opcional.isEmpty() ){
            throw new ValidacionDeIntegridadE("No existe una cita con el código "+codigo);
        }
        return opcional.get();
    }
}
