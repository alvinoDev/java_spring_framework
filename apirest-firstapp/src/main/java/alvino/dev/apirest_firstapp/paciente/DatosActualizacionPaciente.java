package alvino.dev.apirest_firstapp.paciente;

import alvino.dev.apirest_firstapp.direccion.DatosDireccion;
import jakarta.validation.constraints.NotNull;

public record DatosActualizacionPaciente(
        @NotNull Long id,
        String nombre,
        String telefono,
        DatosDireccion direccion
) {
}
