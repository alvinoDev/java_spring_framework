package alvino.dev.apirest_firstapp.domain.consulta.validaciones.cancelamiento;

import alvino.dev.apirest_firstapp.domain.consulta.ConsultaRepository;
import alvino.dev.apirest_firstapp.domain.consulta.DatosCancelarConsulta;
import alvino.dev.apirest_firstapp.domain.validacionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.LocalDateTime;

@Component
public class ValidadorHorarioConAnticipacion implements ValidadorCancelamientoDeConsulta {
    @Autowired
    private ConsultaRepository consultaRepository;

    @Override
    public void validar(DatosCancelarConsulta datos) {
        var consulta = consultaRepository.getReferenceById(datos.idConsulta());
        var ahora = LocalDateTime.now();
        var diferenciaEnHoras = Duration.between(ahora, consulta.getFecha()).toHours();

        if (diferenciaEnHoras < 24) {
            throw new validacionException("¡La consulta solo puede ser cancelada con anticipación mínima de 24 horas!");
        }
    }
}
