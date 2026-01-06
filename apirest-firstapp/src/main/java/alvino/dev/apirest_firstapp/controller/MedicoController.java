package alvino.dev.apirest_firstapp.controller;

import alvino.dev.apirest_firstapp.medico.DatosListaMedico;
import alvino.dev.apirest_firstapp.medico.DatosRegistroMedico;
import alvino.dev.apirest_firstapp.medico.Medico;
import alvino.dev.apirest_firstapp.medico.MedicoRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

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
    public Page<DatosListaMedico> listar(@PageableDefault(size = 10, page = 0, sort = {"nombre"}) Pageable paginacion) {
        return medicoRepository.findAll(paginacion).map(DatosListaMedico::new);
    }

    @Transactional
    @PutMapping
    public void actualizar(@RequestBody @Valid DatosRegistroMedico datos) {}
}
