package alvino.dev.apirest_firstapp.domain.consulta;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record DatosDelleConsulta(
        Long id,
        Long idMedico,
        Long idPaciente,
        LocalDateTime fecha
) {
}
