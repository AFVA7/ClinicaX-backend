package co.edu.uniquindio.clinicaX.servicios.impl;

import co.edu.uniquindio.clinicaX.dto.cita.ItemAtencionDTO;
import co.edu.uniquindio.clinicaX.dto.pqrs.DetallePQRSDTO;
import co.edu.uniquindio.clinicaX.dto.pqrs.ItemPQRSDTO;
import co.edu.uniquindio.clinicaX.dto.RegistroRespuestaDTO;
import co.edu.uniquindio.clinicaX.dto.admin.*;
import co.edu.uniquindio.clinicaX.dto.cita.ItemCitaDTO;
import co.edu.uniquindio.clinicaX.model.enums.EstadoPQRS;
import co.edu.uniquindio.clinicaX.servicios.interfaces.AdministradorServicio;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class AdministradorServicioImpl implements AdministradorServicio {

    private final PQRServicioImpl pqrServicio;
    private final CitaServicioImpl citaServicios;
    private final MedicoServicioImpl medicoServicio;

    @Override
    public int crearMedico(RegistroMedicoDTO medicoDTO) {

        return medicoServicio.crearMedico(medicoDTO);
    }
    @Override
    public String acualizarMedico(DetalleMedicoDTO medicoDTO) throws Exception {
        return medicoServicio.acualizarMedico(medicoDTO);
    }

    @Override
    public String eliminarMedico(int codigo) throws Exception {
        return medicoServicio.eliminarMedico(codigo);
    }

    @Override
    public List<ItemMedicoDto> listarMedicos() throws Exception {
        return medicoServicio.listarMedicos();
    }

    @Override
    public List<ItemPQRSDTO> ListarPQRS()  {
        return pqrServicio.listarPQRS();
    }

    @Override
    public DetallePQRSDTO verDetellaesPQRS(int codigo)  {
        return pqrServicio.verDetallePQRS(codigo);
    }

    @Override
    public List<ItemCitaDTO> listarCitas() {
        return citaServicios.listarCitas();
    }

    @Override
    public List<ItemAtencionDTO> historialDeConsultas(int codigoMedico) {
        return citaServicios.listarTodasCitasMedico(codigoMedico);
    }

    @Override
    public int responderPQRS(RegistroRespuestaDTO registroRespuestaDTO)  {
        return pqrServicio.responderPQRS(registroRespuestaDTO);
    }

    @Override
    public void cambiarEstadoPQRS(int codigoPQRS, EstadoPQRS estadoPQRS) {
        pqrServicio.cambiarEstadoPQRS(codigoPQRS, estadoPQRS);
    }

    @Override
    public DetalleMedicoDTO obtenerMedico(int codigo) throws Exception {
        return medicoServicio.obtenerMedico(codigo);
    }
}
