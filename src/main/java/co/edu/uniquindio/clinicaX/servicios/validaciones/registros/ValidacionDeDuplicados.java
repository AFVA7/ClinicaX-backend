package co.edu.uniquindio.clinicaX.servicios.validaciones.registros;

import co.edu.uniquindio.clinicaX.dto.admin.RegistroMedicoDTO;
import co.edu.uniquindio.clinicaX.dto.paciente.DetallePacienteDTO;
import co.edu.uniquindio.clinicaX.dto.paciente.RegistroPacienteDTO;
import co.edu.uniquindio.clinicaX.infra.errors.ValidacionDeIntegridadE;
import co.edu.uniquindio.clinicaX.model.Paciente;
import co.edu.uniquindio.clinicaX.repositorios.MedicoRepo;
import co.edu.uniquindio.clinicaX.repositorios.PacienteRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class ValidacionDeDuplicados {
    private  final MedicoRepo medicoRepo;
    private  final PacienteRepo pacienteRepo;
    //validación para médico
    public  void validarMedico(RegistroMedicoDTO medicoDTO) {
        if (estaRepetidaCedulaMedico(medicoDTO.cedula())){
            throw new ValidacionDeIntegridadE("La cédula "+medicoDTO.cedula()+" ya está en uso");
        }
        if (estaRepetidoCorreoMedico(medicoDTO.correo())){
            throw new ValidacionDeIntegridadE("El correo "+medicoDTO.correo()+" ya está en uso");
        }
    }

    private boolean estaRepetidaCedulaMedico(String cedula) {
        return medicoRepo.findByCedula(cedula)!=null;
    }

    private boolean estaRepetidoCorreoMedico(String correo) {

        return medicoRepo.findByCorreo(correo)!=null;

    }
    //validación para paciente
    public  void validarPaciente(RegistroPacienteDTO pacienteDTO) {
        if (estaRepetidaCedulaPaciente(pacienteDTO.cedula())){
            throw new ValidacionDeIntegridadE("La cédula "+pacienteDTO.cedula()+" ya está en uso");
        }
        if (estaRepetidoCorreoPaciente(pacienteDTO.correo())){
            throw new ValidacionDeIntegridadE("El correo "+pacienteDTO.correo()+" ya está en uso");
        }
    }

    public void validarActualizacionPaciente(DetallePacienteDTO datos){
        Paciente paciente = pacienteRepo.getReferenceById(datos.codigo());
        // Verifica si la cédula está en uso por otro paciente
        if (estaRepetidaCedulaParaOtroPaciente(datos.cedula(), paciente)) {
            throw new ValidacionDeIntegridadE("La cédula " + datos.cedula() + " ya está en uso");
        }
        // Verifica si el correo está en uso por otro paciente
        if (estaRepetidoCorreoParaOtroPaciente(datos.correo(), paciente)) {
            throw new ValidacionDeIntegridadE("El correo " + datos.correo() + " ya está en uso");
        }

    }
    //TODO
    private boolean estaRepetidoCorreoParaOtroPaciente(String correo, Paciente paciente) {
        Optional<Paciente> pacienteExistente = pacienteRepo.findByCorreoAndCodigoNot(correo, paciente.getCodigo());
        return pacienteExistente.isPresent();
    }

    //TODO
    private boolean estaRepetidaCedulaParaOtroPaciente(String cedula, Paciente paciente) {
        Optional<Paciente> pacienteExistente = pacienteRepo.findByCedulaAndCodigoNot(cedula, paciente.getCodigo());
        return pacienteExistente.isPresent();
    }

    private boolean estaRepetidoCorreoPaciente(String correo) {

        return pacienteRepo.findByCorreo(correo)!=null;
    }

    private boolean estaRepetidaCedulaPaciente(String cedula) {

        return pacienteRepo.findByCedula(cedula)!=null;
    }
}
