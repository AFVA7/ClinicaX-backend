package co.edu.uniquindio.clinicaX.servicios.impl;

import co.edu.uniquindio.clinicaX.dto.*;
import co.edu.uniquindio.clinicaX.dto.cita.AgendarCitaDTO;
import co.edu.uniquindio.clinicaX.dto.cita.DetalleAtencionMedicaDTO;
import co.edu.uniquindio.clinicaX.dto.cita.DetalleCitaDTO;
import co.edu.uniquindio.clinicaX.dto.cita.ItemCitaDTO;
import co.edu.uniquindio.clinicaX.dto.paciente.*;
import co.edu.uniquindio.clinicaX.dto.pqrs.DetallePQRSDTO;
import co.edu.uniquindio.clinicaX.dto.pqrs.ItemPQRSDTO;
import co.edu.uniquindio.clinicaX.dto.pqrs.RegistroPQRDTO;
import co.edu.uniquindio.clinicaX.infra.errors.ValidacionDeIntegridadE;
import co.edu.uniquindio.clinicaX.model.Paciente;
import co.edu.uniquindio.clinicaX.model.Pqrs;
import co.edu.uniquindio.clinicaX.model.enums.EstadoUsuario;
import co.edu.uniquindio.clinicaX.repositorios.CitaRepo;
import co.edu.uniquindio.clinicaX.repositorios.PQRSRepo;
import co.edu.uniquindio.clinicaX.repositorios.PacienteRepo;
import co.edu.uniquindio.clinicaX.servicios.interfaces.PacienteServicio;
import co.edu.uniquindio.clinicaX.servicios.validaciones.registros.ValidacionDeDuplicados;
import jakarta.validation.ValidationException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor//es como escribir el constr de esta clase, instanciando todos sus campos, también se puede usar @AllArgsConstructor
public class PacienteServicioImpl implements PacienteServicio {

    private final PacienteRepo pacienteRepo;
    private final ValidacionDeDuplicados validacion;
    private final CitaServicioImpl citaServiciosImpl;
    private final CitaRepo citaRepo;
    private final PQRServicioImpl pqrServicio;
    private final PQRSRepo pqrsRepo;
    private  final PasswordEncoder passwordEncoder;
    private final CuentaServicioImpl cuentaServicios;
    private final AtencionServicioImpl atencionServicio;

    @Override
    public int registrarse(RegistroPacienteDTO pacienteDTO){
        validacion.validarPaciente(pacienteDTO);
        String passwd = passwordEncoder.encode(pacienteDTO.password());
        Paciente paciente = pacienteRepo.save(new Paciente(pacienteDTO, passwd));
        return paciente.getCodigo();
    }

    @Override
    @Transactional
    public int editarPerfil(DetallePacienteDTO datos) {
        Optional<Paciente> opcional = pacienteRepo.findById(datos.codigo());
        if( opcional.isEmpty() ){
            throw new ValidacionDeIntegridadE("No existe un paciente con el código "+datos.codigo());
        }
        Paciente paciente = opcional.get();
        validacion.validarActualizacionPaciente(datos);
        paciente.actualizar(datos);
        return paciente.getCodigo();
    }

    @Override
    public List<ItemPacienteDTO> listarTodos() {
        List<Paciente> pacientes = pacienteRepo.findAll();
        if (pacientes.isEmpty()) {
            throw new ValidationException("No hay pacientes registrados");
        }
        //Hacemos un mapeo de cada uno de los objetos de tipo Paciente a objetos de tipo ItemPacienteDTO
        return pacientes.stream()
                .filter(p -> p.getEstado() == EstadoUsuario.ACTIVO)
                .map(ItemPacienteDTO::new)
                .toList();
    }

    @Override
    @Transactional
    public String eliminarCuenta(int codigo){
        Optional<Paciente> optional = pacienteRepo.findById(codigo);
        if( optional.isEmpty() ){
            throw new ValidacionDeIntegridadE("No existe un paciente con el código "+codigo);
        }
        Paciente paciente = optional.get();
        paciente.inactivar();
        return "Médico eliminado con éxito";
    }
    public void activarPaciente(int codigo){
        Optional<Paciente> optional = pacienteRepo.findById(codigo);
        if( optional.isEmpty() ){
            throw new ValidacionDeIntegridadE("No existe un paciente con el código "+codigo);
        }
        Paciente paciente = optional.get();
        if(paciente.getEstado()==EstadoUsuario.ACTIVO){
            throw new ValidationException("El paciente ya esta activo");
        }
        paciente.activar();
    }

    @Override
    public DetallePacienteDTO verDetallePaciente(int codigo)  {
        Optional<Paciente> optional = pacienteRepo.findById(codigo);
        if( optional.isEmpty() ){
            throw new ValidacionDeIntegridadE("No existe un paciente con el código "+codigo);
        }
        Paciente paciente = optional.get();
        if(paciente.getEstado()==EstadoUsuario.INACTIVO){
            throw new ValidationException("El paciente esta Inactivo");
        }
        //Hacemos un mapeo de un objeto de tipo Paciente a un objeto de tipo DetallePacienteDTO
        return new DetallePacienteDTO(paciente);
    }

    @Override
    public void recuperarPasswd(String email) throws Exception{
        cuentaServicios.enviarLinkRecuperacion(email);
    }

    @Override
    public int agendarCita(AgendarCitaDTO agendarCitaDTO) throws Exception {
        return citaServiciosImpl.agendarCita(agendarCitaDTO);
    }
    //citas que ya han pasado
    @Override
    public List<ItemCitaDTO> listarHistorial(int codigoPaciente) throws Exception {
        return citaServiciosImpl.listarHistorialPaciente(codigoPaciente);
    }
    @Override
    public List<ItemCitaDTO> listarCitasPendientes(int codigoPaciente) {
        return  citaServiciosImpl.listarCitasPendientesPaciente(codigoPaciente);
    }

    @Override
    public List<ItemCitaDTO> filtrarCitasPorFecha(FiltroBusquedaDTO filtroBusquedaDTO){
        return citaServiciosImpl.filtrarCitasPorFecha(filtroBusquedaDTO);
    }

    @Override
    public List<ItemCitaDTO> filtrarCitasPorMedico(int idMedico) {
        return citaServiciosImpl.filtrarCitasPorMedico(idMedico);
    }

    @Override
    public DetalleAtencionMedicaDTO verDetalleCita(int codigoCita) throws Exception {
        return atencionServicio.verDetalle(codigoCita);
    }

    @Override
    public void crearPQRS(RegistroPQRDTO registroPQRDTO) throws Exception {
        pqrServicio.crearPQRS(registroPQRDTO);
    }

    @Override
    public List<ItemPQRSDTO> listarPQRSPaciente(int codigoPaciente) {
        List<Pqrs> pqrs = pqrsRepo.findAllByCita_PacienteCodigo(codigoPaciente);
        if(pqrs.isEmpty()){
            throw new ValidationException("No existen PQRS creadas para el paciente "+codigoPaciente);
        }
        return pqrs.stream()
                .map(ItemPQRSDTO::new)
                .toList();
    }

    @Override
    public int responderPQRS(RegistroRespuestaDTO registroRespuestaDTO) throws Exception {
        return pqrServicio.responderPQRS(registroRespuestaDTO);
    }

    @Override
    public DetallePQRSDTO verDetallePQR(int codigo) throws Exception {
        return pqrServicio.verDetallePQRS(codigo);
    }
}
