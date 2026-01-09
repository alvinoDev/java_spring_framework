package alvino.dev.apirest_firstapp.domain.consulta;

import alvino.dev.apirest_firstapp.domain.medico.MedicoRepository;
import alvino.dev.apirest_firstapp.domain.paciente.PacienteRepository;
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
        var medico = medicoRepository.findById(datos.idMedico()).get();
        var paciente = pacienteRepository.findById(datos.idPaciente()).get();

        var consulta = new Consulta(null, medico, paciente, datos.fecha());

        consultaRepository.save(consulta);
    }
}
