package alvino.dev.apirest_firstapp.domain.medico;

import alvino.dev.apirest_firstapp.domain.direccion.DatosDireccion;
import jakarta.validation.constraints.NotNull;

public record DatosActualizacionMedico(
        @NotNull Long id,
        String nombre,
        String telefono,
        DatosDireccion direccion
) {
}
