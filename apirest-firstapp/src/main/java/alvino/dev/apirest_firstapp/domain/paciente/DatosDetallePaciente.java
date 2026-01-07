package alvino.dev.apirest_firstapp.domain.paciente;

import alvino.dev.apirest_firstapp.domain.direccion.Direccion;

public record DatosDetallePaciente(
        Long id,
        String nombre,
        String email,
        String documento,
        Direccion direccion
) {
    public DatosDetallePaciente (Paciente paciente) {
        this(
                paciente.getId(),
                paciente.getNombre(),
                paciente.getEmail(),
                paciente.getDocumento(),
                paciente.getDireccion()
        );
    }
}
