package alvino.dev.apirest_firstapp.controller;

import alvino.dev.apirest_firstapp.medico.DatosRegistroMedico;
import alvino.dev.apirest_firstapp.medico.Medico;
import alvino.dev.apirest_firstapp.medico.MedicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/medicos")
public class MedicoController {

    @Autowired
    private MedicoRepository medicoRepository;

    @Transactional
    @PostMapping
    public void registrar(@RequestBody DatosRegistroMedico datos) {
        medicoRepository.save(new Medico(datos));
    }
}
