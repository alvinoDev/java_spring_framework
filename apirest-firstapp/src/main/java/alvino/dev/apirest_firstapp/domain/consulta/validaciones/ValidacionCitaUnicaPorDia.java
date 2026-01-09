package alvino.dev.apirest_firstapp.domain.consulta.validaciones;

import alvino.dev.apirest_firstapp.domain.consulta.ConsultaRepository;
import alvino.dev.apirest_firstapp.domain.consulta.DatosReservaConsulta;
import jakarta.validation.ValidationException;

// nombre del curso: ValidacionPacienteSinOtraConsultaEnElMismoDia
public class ValidacionCitaUnicaPorDia {

    private ConsultaRepository consultaRepository;

    public void validar(DatosReservaConsulta datos) {
        var primerHorario = datos.fecha().withHour(7);
        var ultimoHorario = datos.fecha().withHour(18);

        var pacienteTieneOtraConsultaEnElDia = consultaRepository.existsByPacienteIdAndFechaBettween(datos.idPaciente(), primerHorario, ultimoHorario);
        if( pacienteTieneOtraConsultaEnElDia) {
            throw new ValidationException("Paciente ya tiene una consulta para el dia");
        }
    }
}
