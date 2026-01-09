package alvino.dev.apirest_firstapp.domain.consulta;

import alvino.dev.apirest_firstapp.domain.medico.Medico;
import alvino.dev.apirest_firstapp.domain.medico.MedicoRepository;
import alvino.dev.apirest_firstapp.domain.paciente.PacienteRepository;
import alvino.dev.apirest_firstapp.domain.validacionIDException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReservaDeConsultas {
    @Autowired
    private ConsultaRepository consultaRepository;

    @Autowired
    MedicoRepository medicoRepository;
    @Autowired
    PacienteRepository pacienteRepository;

    public void reservar(DatosReservaConsulta datos) {
        if(!pacienteRepository.existsById(datos.idPaciente())) {
            throw new validacionIDException("No existe un paciente con el ID seleccionado");
        }

        if(datos.idMedico() != null && !medicoRepository.existsById(datos.idMedico())) {
            throw new validacionIDException("No existe un medico con el ID seleccionado");
        }

        var medico = seleccionarMedico(datos);
        var paciente = pacienteRepository.findById(datos.idPaciente()).get();

        var consulta = new Consulta(null, medico, paciente, datos.fecha());

        consultaRepository.save(consulta);
    }

    private Medico seleccionarMedico(DatosReservaConsulta datos) {
        if(datos.idMedico() != null) {
            return medicoRepository.getReferenceById(datos.idMedico());
        }

        if(datos.especialidad() == null) {
            throw new validacionIDException("No existe un medico seleccionado, es necesario seleccionar una especialidad");
        }

        return medicoRepository.seleccionarMedicoAleatorioDisponibleEnLaFecha(datos.especialidad(), datos.fecha());
    }
}
