package alvino.dev.apirest_firstapp.domain.consulta;

import java.time.LocalDateTime;

public record DatosDeConsulta(
        Long id,
        Long idMedico,
        Long idPaciente,
        LocalDateTime fecha
) {
}
