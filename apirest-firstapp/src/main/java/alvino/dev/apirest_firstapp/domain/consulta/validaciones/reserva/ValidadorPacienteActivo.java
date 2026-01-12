package alvino.dev.apirest_firstapp.domain.consulta.validaciones.reserva;

import alvino.dev.apirest_firstapp.domain.consulta.DatosReservaConsulta;
import alvino.dev.apirest_firstapp.domain.paciente.PacienteRepository;
import jakarta.validation.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidadorPacienteActivo implements ValidadorDeConsultas {
    @Autowired
    private PacienteRepository pacienteRepository;

    public void validar(DatosReservaConsulta datos) {
        var pacienteActivo = pacienteRepository.findActivoById(datos.idPaciente());
        if(!pacienteActivo) {
            throw new ValidationException("Paciente no encontrado o inactivo.");
        }
    }
}
