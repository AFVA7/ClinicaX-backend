package co.edu.uniquindio.clinicaX.servicios.impl;

import co.edu.uniquindio.clinicaX.dto.cita.DetalleAtencionMedicaDTO;
import co.edu.uniquindio.clinicaX.dto.cita.ItemAtencionDTO;
import co.edu.uniquindio.clinicaX.dto.medico.RegistroAtencionDTO;
import co.edu.uniquindio.clinicaX.infra.errors.ValidacionDeIntegridadE;
import co.edu.uniquindio.clinicaX.model.Atencion;
import co.edu.uniquindio.clinicaX.model.Cita;
import co.edu.uniquindio.clinicaX.model.enums.EstadoCita;
import co.edu.uniquindio.clinicaX.repositorios.AtencionRepo;
import co.edu.uniquindio.clinicaX.repositorios.CitaRepo;
import co.edu.uniquindio.clinicaX.servicios.interfaces.AtencionServicio;
import co.edu.uniquindio.clinicaX.servicios.validaciones.atender.ValidadorDeAtenciones;
import jakarta.transaction.Transactional;
import jakarta.validation.ValidationException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class AtencionServicioImpl implements AtencionServicio {
    private final AtencionRepo atencionRepo;
    private final CitaRepo citaRepo;
    @Autowired
    List<ValidadorDeAtenciones> validadores;
    @Override
    public int crear(RegistroAtencionDTO datos) {
        Cita cita = validarCita(datos.codigoCita());
        //Se valida que solo pueda atender citas del día actual
        validadores.forEach(v->v.validar(datos));
        Atencion atencion = atencionRepo.save(new Atencion(datos, cita));
        cita.setEstado(EstadoCita.COMPLETADA);
        cita.setAtencion(atencion);
        System.out.println("Atención " + atencion.getCodigo() + " creada");
        return atencion.getCodigo();
    }

    @Override
    public List<ItemAtencionDTO> listar(int codigoMedico) {
        List<Atencion> atenciones = validarLista(codigoMedico);
        return atenciones.stream()
                .map(atencion -> new ItemAtencionDTO(atencion.getCita()))
                .toList();
    }

    @Override
    public DetalleAtencionMedicaDTO update(DetalleAtencionMedicaDTO datos) {
        Atencion atencion = validarAtencion(datos.codigo());
        Cita cita = validarCita(datos.codigoCita());
        atencion.actualizar(datos, cita);
        return new DetalleAtencionMedicaDTO(atencion.getCita());
    }

    @Override
    public String eliminar(int codigo) {
        Atencion atencion = validarAtencion(codigo);
        atencionRepo.delete(atencion);
        return "Médico eliminado con éxito";
    }


    @Override
    public DetalleAtencionMedicaDTO verDetalle(int codigo) {
            Atencion atencion = validarAtencion(codigo);
            return new DetalleAtencionMedicaDTO(atencion.getCita());
    }

    public DetalleAtencionMedicaDTO verDetalleRelacionadoACita(int codigo) {
        Atencion atencion = validarAtencionCita(codigo);
        return new DetalleAtencionMedicaDTO(atencion.getCita());
    }

    private Cita validarCita(int codigo) {
        Optional<Cita> opcional = citaRepo.findById(codigo);
        if (opcional.isEmpty()) {
            throw new ValidacionDeIntegridadE("No existe una cita con el código "+codigo);
        }
        return opcional.get();
    }

    private Atencion validarAtencion (int codigo){
        Optional<Atencion> opcional = atencionRepo.findById(codigo);
        if( opcional.isEmpty() ){
            throw new ValidacionDeIntegridadE("No existe una atención con el código "+codigo);
        }
        return opcional.get();
    }

    public Atencion validarAtencionCita(int codigo){
        Optional<Atencion> opcional = atencionRepo.findAtencionByCitaCodigo(codigo);
        if(opcional.isEmpty()){
            throw new ValidacionDeIntegridadE("Aun no hay atención para la cita con código "+codigo);
        }
        return opcional.get();
    }

    public List<Atencion> validarLista(int codigo){
        List<Atencion> atenciones = atencionRepo.findAllByCita_MedicoCodigo(codigo);
        if (atenciones.isEmpty()) {
            throw new ValidationException("No hay citas atendidas por el medico "+codigo);
        }
        return atenciones;
    }
}
