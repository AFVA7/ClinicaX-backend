package co.edu.uniquindio.clinicaX.servicios.impl;

import co.edu.uniquindio.clinicaX.dto.medico.DetalleDiaLibreDTO;
import co.edu.uniquindio.clinicaX.dto.medico.DiaLibreDTO;
import co.edu.uniquindio.clinicaX.infra.errors.ValidacionDeIntegridadE;
import co.edu.uniquindio.clinicaX.model.*;
import co.edu.uniquindio.clinicaX.repositorios.DiaLibreRepo;
import co.edu.uniquindio.clinicaX.repositorios.MedicoRepo;
import co.edu.uniquindio.clinicaX.servicios.interfaces.DiaLibreServicio;
import co.edu.uniquindio.clinicaX.servicios.validaciones.agendar.ValidadorDeCitas;
import co.edu.uniquindio.clinicaX.servicios.validaciones.diaLibre.ValidadorDiaLibre;
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
public class DiaLibreServicioImpl implements DiaLibreServicio {
    private final DiaLibreRepo diaLibreRepo;
    private final MedicoRepo medicoRepo;
    @Autowired
    List<ValidadorDiaLibre> validadores;
    @Override
    public int crear(DiaLibreDTO diaLibreDTO) {
        Medico medico = validarMedico(diaLibreDTO.codigoMedico());
        //validaciones
        validadores.forEach(v->v.validar(diaLibreDTO));
        DiaLibre diaLibre = diaLibreRepo.save(new DiaLibre(diaLibreDTO,medico));
        System.out.println( "Dia Libre " + diaLibre.getCodigo() + " agendado");
        return diaLibre.getCodigo();
    }

    @Override
    public List<DetalleDiaLibreDTO> listar() {
        List<DiaLibre> diaLibres = diaLibreRepo.findAll();
        if (diaLibres.isEmpty()) {
            throw new ValidationException("No hay dias libres registrados");
        }
        return diaLibres.stream()
                .map(DetalleDiaLibreDTO::new)
                .toList();
    }

    @Override
    public DiaLibreDTO update(DetalleDiaLibreDTO datos) {
        DiaLibre diaLibre = validarDia(datos.codigo());
        Medico medico = validarMedico(datos.codigoMedico());
        diaLibre.actualizar(datos, medico);
        return new DiaLibreDTO(diaLibre);
    }

    @Override
    public String eliminar(int codigo) {
        DiaLibre diaLibre = validarDia(codigo);
        diaLibreRepo.delete(diaLibre);
        return "Médico eliminado con éxito";

    }

    @Override
    public DetalleDiaLibreDTO obtener(int codigo) {
        DiaLibre diaLibre = validarDia(codigo);
        return new DetalleDiaLibreDTO(diaLibre);
    }

    private Medico validarMedico(int codigo) {
        Optional<Medico> opcional = medicoRepo.findById(codigo);
        if (opcional.isEmpty()) {
            throw new ValidationException("No se encontró el médico con código "+codigo);
        }
        return opcional.get();
    }

    private DiaLibre validarDia (int codigo){
        Optional<DiaLibre> opcional = diaLibreRepo.findByMedicoCodigo(codigo);
        if( opcional.isEmpty() ){
            throw new ValidacionDeIntegridadE("No existe un dia libre para el médico "+codigo);
        }
        return opcional.get();
    }
}
