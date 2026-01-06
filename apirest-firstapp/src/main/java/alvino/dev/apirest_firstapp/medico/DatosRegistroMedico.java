package alvino.dev.apirest_firstapp.medico;

import alvino.dev.apirest_firstapp.direccion.DatosDireccion;

public record DatosRegistroMedico(
        String nombre,
        String email,
        String documento,
        Especialidad especialidad,
        DatosDireccion direccion
) {
}
