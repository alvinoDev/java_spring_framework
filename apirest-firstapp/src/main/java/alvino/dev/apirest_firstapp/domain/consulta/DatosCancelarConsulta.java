package alvino.dev.apirest_firstapp.domain.consulta;

import jakarta.validation.constraints.NotNull;

public record DatosCancelarConsulta(
        @NotNull
        Long idConsulta,
        @NotNull
        MotivoCancelamiento motivo
) {

}
