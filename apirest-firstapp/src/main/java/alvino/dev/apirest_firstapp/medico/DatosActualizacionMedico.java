package alvino.dev.apirest_firstapp.medico;

import alvino.dev.apirest_firstapp.direccion.DatosDireccion;

public record DatosActualizacionMedico(
        Long id,
        String nombre,
        String telefono,
        DatosDireccion direccion
) {
}
