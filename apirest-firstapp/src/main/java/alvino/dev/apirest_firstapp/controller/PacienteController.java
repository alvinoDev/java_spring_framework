package alvino.dev.apirest_firstapp.controller;

import alvino.dev.apirest_firstapp.medico.DatosListaMedico;
import alvino.dev.apirest_firstapp.paciente.DatosListaPaciente;
import alvino.dev.apirest_firstapp.paciente.DatosRegistroPaciente;
import alvino.dev.apirest_firstapp.paciente.Paciente;
import alvino.dev.apirest_firstapp.paciente.PacienteRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/pacientes")
public class PacienteController {

    @Autowired
    private PacienteRepository pacienteRepository;

    @Transactional
    @PostMapping
    public void registrar(@RequestBody @Valid DatosRegistroPaciente datos) {
        pacienteRepository.save(new Paciente(datos));
    }

    @GetMapping
    public Page<DatosListaPaciente> listar(@PageableDefault (size = 10, page = 0, sort = {"nombre"}) Pageable paginacion) {
        return pacienteRepository.findAllByActivoTrue(paginacion).map(DatosListaPaciente::new);
    }
}
