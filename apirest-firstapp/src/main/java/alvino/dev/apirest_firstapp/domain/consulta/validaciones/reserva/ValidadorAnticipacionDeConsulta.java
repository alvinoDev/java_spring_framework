package alvino.dev.apirest_firstapp.domain.consulta.validaciones.reserva;

import alvino.dev.apirest_firstapp.domain.consulta.DatosReservaConsulta;
import jakarta.validation.ValidationException;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.LocalDateTime;

@Component
public class ValidadorAnticipacionDeConsulta implements ValidadorDeConsultas {
    public void validar(DatosReservaConsulta datos) {
        var fechaConsulta = datos.fecha();
        var ahora = LocalDateTime.now();
        var diferenciaEnMinutos = Duration.between(ahora, fechaConsulta).toMinutes();
        if(diferenciaEnMinutos < 30) {
            throw new ValidationException("Las consultas deben programarse con al menos 30 minutos de anticipación.");
        }
    }
}
