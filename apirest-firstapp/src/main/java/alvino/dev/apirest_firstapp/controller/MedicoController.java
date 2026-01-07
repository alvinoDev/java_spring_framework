package alvino.dev.apirest_firstapp.controller;

import alvino.dev.apirest_firstapp.domain.medico.*;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/medicos")
public class MedicoController {

    @Autowired
    private MedicoRepository medicoRepository;

    @Transactional
    @PostMapping
    public ResponseEntity registrar(@RequestBody @Valid DatosRegistroMedico datos, UriComponentsBuilder uriBuilder) {
        var medico = new Medico(datos);
        medicoRepository.save(medico);
        var uri = uriBuilder.path("/medicos/{id}").buildAndExpand(medico.getId()).toUri();
        return ResponseEntity.created(uri).body(new DatosDetalleMedico(medico));
    }

    @GetMapping
    public ResponseEntity<Page<DatosListaMedico>> listar(@PageableDefault(size = 10, page = 0, sort = {"nombre"}) Pageable paginacion) {
        var data = medicoRepository.findAllByActivoTrue(paginacion).map(DatosListaMedico::new);

        return ResponseEntity.ok(data);
    }

    @GetMapping("/{id}")
    public ResponseEntity mostrar(@PathVariable Long id) {
        var medico = medicoRepository.getReferenceById(id);
        return ResponseEntity.ok(new DatosDetalleMedico(medico));
    }

    @Transactional
    @PutMapping
    public ResponseEntity actualizar(@RequestBody @Valid DatosActualizacionMedico datos) {
        var medico = medicoRepository.getReferenceById(datos.id());
        medico.actualizarInformacion(datos);

        return  ResponseEntity.ok(new DatosDetalleMedico(medico));
    }

    @Transactional
    @DeleteMapping("/{id}")
    public ResponseEntity eliminar(@PathVariable Long id) {
        var medico = medicoRepository.getReferenceById(id);
        medico.eliminacionLogica();
        return ResponseEntity.noContent().build();
    }
}
