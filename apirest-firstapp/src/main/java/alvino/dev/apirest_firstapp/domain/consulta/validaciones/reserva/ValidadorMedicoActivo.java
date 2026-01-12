package alvino.dev.apirest_firstapp.domain.consulta.validaciones.reserva;

import alvino.dev.apirest_firstapp.domain.consulta.DatosReservaConsulta;
import alvino.dev.apirest_firstapp.domain.medico.MedicoRepository;
import jakarta.validation.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidadorMedicoActivo implements ValidadorDeConsultas {
    @Autowired
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
