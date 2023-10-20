package co.edu.uniquindio.clinicaX.model;

import co.edu.uniquindio.clinicaX.dto.admin.DetalleMedicoDTO;
import co.edu.uniquindio.clinicaX.dto.admin.RegistroMedicoDTO;
import co.edu.uniquindio.clinicaX.model.enums.Especialidad;
import co.edu.uniquindio.clinicaX.model.enums.EstadoUsuario;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Medico extends Usuario implements Serializable {
    @Column(nullable = false)
    //@Enumerated(EnumType.STRING)//Para guardar el String como tal del enum.
    private Especialidad especialidad;

    @OneToMany(mappedBy = "medico")
    private List<DiaLibre> diasLibres;

    @OneToMany(mappedBy = "medico")
    private List<HorarioMedico> horarios;

    @OneToMany(mappedBy = "medico")
    private List<Cita> citas;

    public Medico(RegistroMedicoDTO medicoDTO, String passwd){
        this.setCedula(medicoDTO.cedula());
        this.setTelefono(medicoDTO.telefono());
        this.setNombre(medicoDTO.nombre());
        this.setEspecialidad(medicoDTO.especialidad());
        this.setCiudad(medicoDTO.ciudad());
        this.setCorreo(medicoDTO.correo());
        this.setPasswd(passwd);
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
