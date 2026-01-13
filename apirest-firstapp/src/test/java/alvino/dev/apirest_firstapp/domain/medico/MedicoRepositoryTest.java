package alvino.dev.apirest_firstapp.domain.medico;

import alvino.dev.apirest_firstapp.domain.consulta.Consulta;
import alvino.dev.apirest_firstapp.domain.direccion.DatosDireccion;
import alvino.dev.apirest_firstapp.domain.paciente.DatosRegistroPaciente;
import alvino.dev.apirest_firstapp.domain.paciente.Paciente;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;
import org.springframework.boot.jdbc.test.autoconfigure.AutoConfigureTestDatabase;
import org.springframework.test.context.ActiveProfiles;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.TemporalAdjusters;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("test")
class MedicoRepositoryTest {

    @Autowired
    private MedicoRepository medicoRepository;

    @Autowired
    private EntityManager entityManager;

    @Test
    @DisplayName("Deberia devolver NULL cuando el medico buscado existe pero no esta disponible en esa fecha.")
    void seleccionarMedicoAleatorioDisponibleEnLaFechaEscenario1() {
        // GIVEN: Preparación del escenario
        // 1. Definimos una fecha específica (el próximo lunes a las 10:00)
        var lunesSiguienteALas10 = LocalDate.now().with(TemporalAdjusters.next(DayOfWeek.MONDAY)).atTime(10, 0);

        // 2. Registramos los datos necesarios en la base de datos de prueba
        var medico = registrarMedico("Medico1", "medico@mail.com", "12345", Especialidad.CARDIOLOGIA);
        var paciente = registrarPaciente("Paciente1", "paciente@mail.com", "123456789");

        // 3. Ocupamos la agenda del médico creando una consulta en ese mismo horario
        registrarConsulta(medico, paciente, lunesSiguienteALas10);

        // WHEN: Ejecución de la acción a probar
        // Intentamos buscar un médico disponible para la especialidad de Cardiología en el horario ocupado
        var medicoLibre = medicoRepository.seleccionarMedicoAleatorioDisponibleEnLaFecha(Especialidad.CARDIOLOGIA, lunesSiguienteALas10);

        // THEN: Verificación del resultado esperado
        // El resultado debe ser nulo porque el único médico registrado ya está ocupado
        assertThat(medicoLibre).isNull();
    }

    @Test
    @DisplayName("Deberia devolver MEDICO cuando el medico buscado esta disponible en esa fecha.")
    void seleccionarMedicoAleatorioDisponibleEnLaFechaEscenario2() {
        // GIVEN: Preparación de un escenario con disponibilidad
        // 1. Definimos una fecha futura para la consulta (próximo lunes a las 10:00)
        var lunesSiguienteALas10 = LocalDate.now().with(TemporalAdjusters.next(DayOfWeek.MONDAY)).atTime(10, 0);

        // 2. Registramos un médico en el sistema con la especialidad requerida
        // por lo tanto, el médico tiene su agenda totalmente libre para esta fecha.
        var medico = registrarMedico("Medico1", "medico@mail.com", "12345", Especialidad.CARDIOLOGIA);

        // WHEN: Ejecución de la búsqueda
        // Solicitamos al repositorio un médico de Cardiología para el horario libre
        var medicoLibre = medicoRepository.seleccionarMedicoAleatorioDisponibleEnLaFecha(Especialidad.CARDIOLOGIA, lunesSiguienteALas10);

        // THEN: Verificación del resultado
        // El sistema debe ser capaz de encontrar y devolver al médico que registramos,
        // ya que cumple con la especialidad y no tiene conflictos de horario.
        assertThat(medicoLibre).isEqualTo(medico);
    }

    // METODOS ADICIONALES PARA LA CLASE
    private void registrarConsulta(Medico medico, Paciente paciente, LocalDateTime fecha) {
        entityManager.persist(new Consulta(null, medico, paciente, fecha, null));
    }

    private Medico registrarMedico(String nombre, String email, String documento, Especialidad especialidad) {
        var medico = new Medico(datosMedico(nombre, email, documento, especialidad));
        entityManager.persist(medico);
        return medico;
    }

    private Paciente registrarPaciente(String nombre, String email, String documento) {
        var paciente = new Paciente(datosPaciente(nombre, email, documento));
        entityManager.persist(paciente);
        return paciente;
    }

    private DatosRegistroMedico datosMedico(String nombre, String email, String documento, Especialidad especialidad) {
        return new DatosRegistroMedico(
                nombre,
                email,
                "61445484848",
                documento,
                especialidad,
                datosDireccion()
        );
    }

    private DatosRegistroPaciente datosPaciente(String nombre, String email, String documento) {
        return new DatosRegistroPaciente(
                nombre,
                email,
                "61445484848",
                documento,
                datosDireccion()
        );
    }

    private DatosDireccion datosDireccion() {
        return  new DatosDireccion("Calle SAN MARTIN", "1234", "1", "GERONIMO", "0000", "Ciudad Z", "Generacion X");
    }
}