package co.edu.uniquindio.clinicaX.servicios.impl;

import co.edu.uniquindio.clinicaX.model.enums.*;
import co.edu.uniquindio.clinicaX.servicios.interfaces.ClinicaServicio;

import java.util.List;

public class ClinicaServicioImpl implements ClinicaServicio {
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
}
