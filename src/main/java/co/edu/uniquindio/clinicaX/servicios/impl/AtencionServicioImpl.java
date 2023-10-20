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
import co.edu.uniquindio.clinicaX.servicios.interfaces.AtencionServicios;
import jakarta.transaction.Transactional;
import jakarta.validation.ValidationException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class AtencionServicioImpl implements AtencionServicios {
    private final AtencionRepo atencionRepo;
    private final CitaRepo citaRepo;
    @Override
    public int crear(RegistroAtencionDTO datos) {
        Cita cita = validarCita(datos.codigoCita());
        Atencion atencion = atencionRepo.save(new Atencion(datos, cita));
        cita.setEstado(EstadoCita.COMPLETADA);
        cita.setAtencion(atencion);
        System.out.println("Atención " + atencion.getCodigo() + " creada");
        return atencion.getCodigo();
    }

    @Override
    public List<ItemAtencionDTO> listar(int codigoMedico) {
        List<Atencion> atenciones = atencionRepo.findAllByCita_MedicoCodigo(codigoMedico);
        if (atenciones.isEmpty()) {
            throw new ValidationException("No hay citas atendidas por el medico "+codigoMedico);
        }
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

            Optional<Atencion> optional = atencionRepo.findById(codigo);
            if( optional.isEmpty() ){
                throw new ValidacionDeIntegridadE("No existe una atención con el código "+codigo);
            }
            Atencion atencion = optional.get();
            return new DetalleAtencionMedicaDTO(atencion.getCita());


    }

    private Cita validarCita(int codigo) {
        Optional<Cita> opcional = citaRepo.findById(codigo);
        if (opcional.isEmpty()) {
            throw new ValidationException("No se encontró la cita");
        }
        return opcional.get();
    }

    private Atencion validarAtencion (int codigo){
        System.out.println(codigo);
        Optional<Atencion> opcional = atencionRepo.findById(codigo);
        if( opcional.isEmpty() ){
            throw new ValidacionDeIntegridadE("No existe una atención con el código "+codigo);
        }
        return opcional.get();
    }
}
