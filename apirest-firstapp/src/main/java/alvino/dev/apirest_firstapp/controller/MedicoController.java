package alvino.dev.apirest_firstapp.controller;

import alvino.dev.apirest_firstapp.medico.DatosListaMedico;
import alvino.dev.apirest_firstapp.medico.DatosRegistroMedico;
import alvino.dev.apirest_firstapp.medico.Medico;
import alvino.dev.apirest_firstapp.medico.MedicoRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/medicos")
public class MedicoController {

    @Autowired
    private MedicoRepository medicoRepository;

    @Transactional
    @PostMapping
    public void registrar(@RequestBody @Valid DatosRegistroMedico datos) {
        medicoRepository.save(new Medico(datos));
    }

    @GetMapping
    public List<DatosListaMedico> listar() {
        return medicoRepository.findAll().stream().map(DatosListaMedico::new).toList();
    }
}
