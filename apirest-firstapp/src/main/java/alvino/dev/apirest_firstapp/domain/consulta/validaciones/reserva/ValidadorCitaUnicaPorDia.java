package alvino.dev.apirest_firstapp.domain.consulta.validaciones.reserva;

import alvino.dev.apirest_firstapp.domain.consulta.ConsultaRepository;
import alvino.dev.apirest_firstapp.domain.consulta.DatosReservaConsulta;
import jakarta.validation.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

// nombre del curso: ValidacionPacienteSinOtraConsultaEnElMismoDia
@Component
public class ValidadorCitaUnicaPorDia implements ValidadorDeConsultas {

    @Autowired
    private ConsultaRepository consultaRepository;

    public void validar(DatosReservaConsulta datos) {
        var primerHorario = datos.fecha().withHour(7);
        var ultimoHorario = datos.fecha().withHour(18);

        var pacienteTieneOtraConsultaEnElDia = consultaRepository.existsByPacienteIdAndFechaBetween(datos.idPaciente(), primerHorario, ultimoHorario);
        if( pacienteTieneOtraConsultaEnElDia) {
            throw new ValidationException("Paciente ya tiene una consulta para el dia");
        }
    }
}
