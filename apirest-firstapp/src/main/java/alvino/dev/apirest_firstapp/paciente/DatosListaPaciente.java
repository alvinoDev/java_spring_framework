package alvino.dev.apirest_firstapp.paciente;

import alvino.dev.apirest_firstapp.medico.Especialidad;

public record DatosListaPaciente(
        Long id,
        String nombre,
        String email,
        String documento
) {
    public DatosListaPaciente(Paciente paciente) {
        this( paciente.getId(), paciente.getNombre(), paciente.getEmail(), paciente.getDocumento() );
    }
}
