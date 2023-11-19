package co.edu.uniquindio.clinicaX.servicios.impl;

import co.edu.uniquindio.clinicaX.dto.admin.ItemMedicoDto;
import co.edu.uniquindio.clinicaX.model.enums.*;
import co.edu.uniquindio.clinicaX.servicios.interfaces.ClinicaServicio;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ClinicaServicioImpl implements ClinicaServicio {
    private final MedicoServicioImpl medicoServicio;
    @Override
    public List<Ciudad> listarCiudades() {
        return List.of(Ciudad.values());
    }

    @Override
    public List<Especialidad> listarEspecialeidades() {
        return List.of(Especialidad.values());
    }

    @Override
    public List<TipoSangre> listarTipoSangre() {
        return List.of(TipoSangre.values());
    }

    @Override
    public List<Eps> listarEps() {
        return List.of(Eps.values());
    }

    @Override
    public List<EstadoCita> listarEstadoCita() {
        return List.of(EstadoCita.values());
    }

    @Override
    public List<EstadoPQRS> listarEstadoPQRS() {
        return List.of(EstadoPQRS.values());
    }

    @Override
    public List<ItemMedicoDto> listarMedicos() throws Exception {
        return medicoServicio.listarMedicos();
    }

    @Override
    public List<MotivoCancelamiento> listarMotivosCancelamiento() {
        return List.of(MotivoCancelamiento.values());
    }

    @Override
    public List<TipoPQRS> listarTipoPQRS() {
        return List.of(TipoPQRS.values());
    }
}
