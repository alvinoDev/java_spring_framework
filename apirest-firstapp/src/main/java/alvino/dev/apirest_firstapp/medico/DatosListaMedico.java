package alvino.dev.apirest_firstapp.medico;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public record DatosListaMedico(
        String nombre,
        String email,
        String documento,
        Especialidad especialidad
) {
    public DatosListaMedico(Medico medico) {
        this( medico.getNombre(), medico.getEmail(), medico.getDocumento(), medico.getEspecialidad() );
    }
}
