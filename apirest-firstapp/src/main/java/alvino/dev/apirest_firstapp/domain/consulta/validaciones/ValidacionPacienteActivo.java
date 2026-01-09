package alvino.dev.apirest_firstapp.domain.consulta.validaciones;

import alvino.dev.apirest_firstapp.domain.consulta.DatosReservaConsulta;
import alvino.dev.apirest_firstapp.domain.paciente.PacienteRepository;
import jakarta.validation.ValidationException;

public class ValidacionPacienteActivo {
    private PacienteRepository pacienteRepository;

    public void validar(DatosReservaConsulta datos) {
        var pacienteActivo = pacienteRepository.findActivoById(datos.idPaciente());
        if(!pacienteActivo) {
            throw new ValidationException("Paciente no encontrado o inactivo.");
        }
    }
}
