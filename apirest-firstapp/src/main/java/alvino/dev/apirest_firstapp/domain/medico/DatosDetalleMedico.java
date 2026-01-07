package alvino.dev.apirest_firstapp.domain.medico;

import alvino.dev.apirest_firstapp.domain.direccion.Direccion;

public record DatosDetalleMedico(
        Long id,
        String nombre,
        String email,
        String documento,
        Especialidad especialidad,
        Direccion direccion
) {
    public DatosDetalleMedico(Medico medico) {
        this(
                medico.getId(),
                medico.getNombre(),
                medico.getEmail(),
                medico.getDocumento(),
                medico.getEspecialidad(),
                medico.getDireccion()
        );
    }
}
