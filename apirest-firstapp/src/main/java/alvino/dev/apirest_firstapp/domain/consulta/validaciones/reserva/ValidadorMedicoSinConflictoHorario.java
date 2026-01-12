package alvino.dev.apirest_firstapp.domain.consulta.validaciones.reserva;

import alvino.dev.apirest_firstapp.domain.consulta.ConsultaRepository;
import alvino.dev.apirest_firstapp.domain.consulta.DatosReservaConsulta;
import jakarta.validation.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

// nombre del curso: ValidacionMedicoConOtraConsultaEnElMismoHorario
@Component
public class ValidadorMedicoSinConflictoHorario implements ValidadorDeConsultas {
    @Autowired
    private ConsultaRepository consultaRepository;

    public void validar(DatosReservaConsulta datos) {
        var medicoTieneOtraConsultaEnElMismoHorario = consultaRepository.existsByMedicoIdAndFechaAndMotivoCancelamientoIsNull(datos.idMedico(), datos.fecha());
        if(medicoTieneOtraConsultaEnElMismoHorario){
            throw new ValidationException("El medico ya tiene una consulta en la misma fecha y horario");
        }
    }
}
