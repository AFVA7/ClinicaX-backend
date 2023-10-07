package com.uniquindio.edu.clinicaX.model;

import com.uniquindio.edu.clinicaX.dto.admin.DetalleMedicoDTO;
import com.uniquindio.edu.clinicaX.dto.admin.RegistroMedicoDTO;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.*;

import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Medico extends Usuario {
    private Especialidad especialidad;
    @OneToMany(mappedBy = "medico")
    private List<DiaLibre> diasLibres;
    @OneToMany(mappedBy = "medico")
    private List<HorarioMedico> horarios;
    @OneToMany(mappedBy = "medico")
    private List<Cita> citas;

    public void registrar(RegistroMedicoDTO medicoDTO){
        this.setCedula(medicoDTO.cedula());
        this.setTelefono(medicoDTO.telefono());
        this.setNombre(medicoDTO.nombre());
        this.setEspecialidad(medicoDTO.especialidad());
        this.setCiudad(medicoDTO.ciudad());
        this.setCorreo(medicoDTO.correo());
        this.setPasswd(medicoDTO.password());
        this.setUrlFoto(medicoDTO.urlFoto());
        this.setEstado(EstadoUsuario.ACTIVO);
    }
    public void actualizar(DetalleMedicoDTO medicoDTO){
        this.setCedula(medicoDTO.cedula());
        this.setTelefono(medicoDTO.telefono());
        this.setNombre(medicoDTO.nombre());
        this.setEspecialidad(medicoDTO.especialidad());
        this.setCiudad(medicoDTO.ciudad());
        this.setCorreo(medicoDTO.correo());
        this.setUrlFoto(medicoDTO.urlFoto());
    }
}
