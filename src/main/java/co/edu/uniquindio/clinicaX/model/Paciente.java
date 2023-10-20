package co.edu.uniquindio.clinicaX.model;

import co.edu.uniquindio.clinicaX.dto.paciente.DetallePacienteDTO;
import co.edu.uniquindio.clinicaX.dto.paciente.RegistroPacienteDTO;
import co.edu.uniquindio.clinicaX.model.enums.Eps;
import co.edu.uniquindio.clinicaX.model.enums.EstadoUsuario;
import co.edu.uniquindio.clinicaX.model.enums.TipoSangre;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;


@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Paciente extends Usuario implements Serializable {
    @Column(nullable = false)
    private LocalDate fechaNacimiento;

    private String alergias;

    @Column(nullable = false)
    private TipoSangre tipoSangre;

    @Column(nullable = false)
    private Eps eps;

    @OneToMany(mappedBy = "paciente")
    private List<Cita> citas;

    public Paciente(RegistroPacienteDTO pacienteDTO, String passwd) {
        //datos de la cuenta
        this.setCorreo(pacienteDTO.correo());
        this.setPasswd(passwd);

        //datos del usuario
        this.setNombre(pacienteDTO.nombre());
        this.setCedula(pacienteDTO.cedula());
        this.setTelefono(pacienteDTO.telefono());
        this.setCiudad(pacienteDTO.ciudad());
        this.setUrlFoto(pacienteDTO.urlFoto());

        //datos del paciente
        this.setFechaNacimiento(pacienteDTO.fechaNacimiento());
        this.setEps(pacienteDTO.eps());
        this.setAlergias(pacienteDTO.alergias());
        this.setTipoSangre(pacienteDTO.tipoSangre());
    }

    public void actualizar(DetallePacienteDTO datos) {
        //Datos de la Cuenta
        this.setCorreo( datos.correo() );
        //Datos del Usuario
        this.setNombre( datos.nombre() );
        this.setCedula( datos.cedula() );
        this.setTelefono( datos.telefono() );
        this.setCiudad( datos.ciudad() );
        this.setUrlFoto( datos.urlFoto() );
        //Datos del Paciente
        this.setFechaNacimiento( datos.fechaNacimiento() );
        this.setEps( datos.eps() );
        this.setAlergias( datos.alergias() );
        this.setTipoSangre( datos.tipoSangre() );
    }

    public void setearDatos(){

    }

    public void inactivar() {
        this.setEstado(EstadoUsuario.INACTIVO);
    }
}
