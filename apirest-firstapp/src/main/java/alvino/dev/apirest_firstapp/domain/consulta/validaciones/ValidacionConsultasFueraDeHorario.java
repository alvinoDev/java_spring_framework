package alvino.dev.apirest_firstapp.domain.consulta.validaciones;

import alvino.dev.apirest_firstapp.domain.consulta.DatosReservaConsulta;
import alvino.dev.apirest_firstapp.domain.validacionException;

import java.time.DayOfWeek;

public class ValidacionConsultasFueraDeHorario {
    public void validar(DatosReservaConsulta datos) {
        var fechaConsulta = datos.fecha();
        var domingo = fechaConsulta.getDayOfWeek().equals(DayOfWeek.SUNDAY);
        var horarioAntesDeAperturaClinica = fechaConsulta.getHour() < 7;
        var horarioDespuesDeCierreClinica = fechaConsulta.getHour() > 18;

        if(domingo || horarioAntesDeAperturaClinica || horarioDespuesDeCierreClinica) {
            throw new validacionException("Hora seleccionado fuera del horario de atención de la clínica");
        }
    }
}
