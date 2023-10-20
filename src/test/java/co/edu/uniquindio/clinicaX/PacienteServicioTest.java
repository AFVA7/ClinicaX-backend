package co.edu.uniquindio.clinicaX;

import co.edu.uniquindio.clinicaX.dto.RegistroRespuestaDTO;
import co.edu.uniquindio.clinicaX.dto.cita.DetalleAtencionMedicaDTO;
import co.edu.uniquindio.clinicaX.dto.cita.ItemCitaDTO;
import co.edu.uniquindio.clinicaX.dto.paciente.DetallePacienteDTO;
import co.edu.uniquindio.clinicaX.dto.paciente.FiltroBusquedaDTO;
import co.edu.uniquindio.clinicaX.dto.paciente.ItemPacienteDTO;
import co.edu.uniquindio.clinicaX.dto.paciente.RegistroPacienteDTO;
import co.edu.uniquindio.clinicaX.dto.pqrs.DetallePQRSDTO;
import co.edu.uniquindio.clinicaX.dto.pqrs.ItemPQRSDTO;
import co.edu.uniquindio.clinicaX.dto.pqrs.RegistroPQRDTO;
import co.edu.uniquindio.clinicaX.model.Cita;
import co.edu.uniquindio.clinicaX.model.Pqrs;
import co.edu.uniquindio.clinicaX.model.enums.Ciudad;
import co.edu.uniquindio.clinicaX.model.enums.Eps;
import co.edu.uniquindio.clinicaX.model.enums.TipoSangre;
import co.edu.uniquindio.clinicaX.repositorios.PacienteRepo;
import co.edu.uniquindio.clinicaX.servicios.impl.PacienteServicioImpl;
import jakarta.transaction.Transactional;
import jakarta.validation.ValidationException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@SpringBootTest
@Transactional
public class PacienteServicioTest {
    @Autowired
    private PacienteServicioImpl pacienteServicio;

    @Test
    public void reqistrarTest() throws Exception {
        //Creamos un objeto con los datos del paciente
        //List<Alergia> listaDeAlergias = List.of(Alergia.POLVO, Alergia.POLEN);
        RegistroPacienteDTO pacienteDTO = new RegistroPacienteDTO(
                "1097222222",
                "Pepito Perez",
                "3243434",
                "aquí va la url de la foto",
                Ciudad.ARMENIA,
                LocalDate.of(1990, 10, 7),
                "El polvo y el polen me hacen estornudar",
                //listaDeAlergias,
                Eps.NUEVA_EPS,
                TipoSangre.A_POSITIVO,
                "pepitoperez@email.com",
                "12345");
        //Guardamos el registro usando el método del servicio
        int nuevo = pacienteServicio.registrarse(pacienteDTO);
        //Comprobamos que sí haya quedado guardado. Si se guardó debe retornar el código (no 0).
        Assertions.assertNotEquals(0, nuevo);
    }

    @Test
    @Sql("classpath:dataset.sql")
    public void actualizarTest() throws Exception {
        //Para actualizar el paciente primero lo obtenemos
        DetallePacienteDTO guardado = pacienteServicio.verDetallePaciente(1);
        //Le modificamos el número de teléfono, lo demás lo dejamos igual
        DetallePacienteDTO modificado = new DetallePacienteDTO(
                guardado.codigo(),
                guardado.cedula(),
                guardado.nombre(),
                "111111",
                guardado.correo(),
                guardado.fechaNacimiento(),
                guardado.urlFoto(),
                guardado.ciudad(),
                guardado.eps(),
                guardado.tipoSangre(),
                guardado.alergias());
        //Se invoca el servicio de actualizar los datos
        pacienteServicio.editarPerfil(modificado);
        //Se obtiene nuevamente el paciente para comprobar que sí se haya actualizado
        DetallePacienteDTO objetoModificado = pacienteServicio.verDetallePaciente(1);
        //Se comprueba que el teléfono del paciente sea el que se le asignó en la actualización
        Assertions.assertEquals("111111", objetoModificado.telefono());
    }

    @Test
    @Sql("classpath:dataset.sql")
    public void eliminarTest() throws Exception {
        //Se borra por ejemplo el paciente con el código 1
        pacienteServicio.eliminarCuenta(1);
        //Si intentamos buscar un paciente con el código del paciente borrado debemos obtener una excepción indicando que ya no existe
        Assertions.assertThrows(Exception.class, () -> pacienteServicio.verDetallePaciente(1));
    }

    @Test
    @Sql("classpath:dataset.sql")
    public void listarTest() {
        //Obtenemos la lista de todos los pacientes
        List<ItemPacienteDTO> lista = pacienteServicio.listarTodos();
        lista.forEach(System.out::println);
        //Si en el dataset creamos 5 pacientes, entonces el tamaño de la lista debe ser 5
        Assertions.assertEquals(5, lista.size());
    }

    @Test
    @Sql("classpath:dataset.sql")
    public void listarCitasPacienteTest() throws Exception {
        List<ItemCitaDTO> lista = pacienteServicio.listarCitasPaciente(1);
        lista.forEach(System.out::println);
        Assertions.assertEquals(1, lista.size());
    }

    @Test
    @Sql("classpath:dataset.sql")
    public void filtrarCitasPorFechaTest(){
        FiltroBusquedaDTO filtroBusquedaDTO = new FiltroBusquedaDTO(
                1,
                LocalDateTime.of(2023,10,12,10,30),
                LocalDateTime.of(2023,11,12,10,30)
        );
        List<ItemCitaDTO> lista = pacienteServicio.filtrarCitasPorFecha(filtroBusquedaDTO);
        lista.forEach(System.out::println);
        Assertions.assertEquals(1, lista.size());
    }

    @Test
    @Sql("classpath:dataset.sql")
    public void filtrarCitasPorMedicoTest() {
        List<ItemCitaDTO> lista = pacienteServicio.filtrarCitasPorMedico(6);
        lista.forEach(System.out::println);
        Assertions.assertEquals(2, lista.size());
    }

    @Test
    @Sql("classpath:dataset.sql")
    public void listarPQRSPacienteTest() {
        List<ItemPQRSDTO> lista = pacienteServicio.listarPQRSPaciente(1);
        lista.forEach(System.out::println);
        Assertions.assertEquals(1, lista.size());
    }
}
