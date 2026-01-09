package alvino.dev.apirest_firstapp.domain.consulta.validaciones;

import alvino.dev.apirest_firstapp.domain.consulta.DatosReservaConsulta;
import alvino.dev.apirest_firstapp.domain.medico.MedicoRepository;
import jakarta.validation.ValidationException;

public class ValidacionMedicoActivo {
    private MedicoRepository  medicoRepository;

    public void validar(DatosReservaConsulta datos) {
        //Eleccion del medico opcional
        if(datos.idMedico() == null) { return; }

        var medicoActivo = medicoRepository.findActivoById(datos.idMedico());
        if(!medicoActivo) {
            throw new ValidationException("Medico no encontrado o inactivo.");
        }
    }
}
