package alvino.dev.apirest_firstapp.domain.consulta.validaciones;

import alvino.dev.apirest_firstapp.domain.consulta.ConsultaRepository;
import alvino.dev.apirest_firstapp.domain.consulta.DatosReservaConsulta;
import jakarta.validation.ValidationException;

// nombre del curso: ValidacionMedicoConOtraConsultaEnElMismoHorario
public class ValidacionMedicoSinConflictoHorario {
    private ConsultaRepository consultaRepository;

    public void validar(DatosReservaConsulta datos) {
        var medicoTieneOtraConsultaEnElMismoHorario = consultaRepository.existsByMedicoIdAndFecha(datos.idMedico(), datos.fecha());
        if(medicoTieneOtraConsultaEnElMismoHorario){
            throw new ValidationException("El medico ya tiene una consulta en la misma fecha y horario");
        }
    }
}
