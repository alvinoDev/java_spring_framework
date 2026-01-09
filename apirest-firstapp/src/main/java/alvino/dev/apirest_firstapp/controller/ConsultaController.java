package alvino.dev.apirest_firstapp.controller;

import alvino.dev.apirest_firstapp.domain.consulta.DatosDelleConsulta;
import alvino.dev.apirest_firstapp.domain.consulta.DatosReservaConsulta;
import alvino.dev.apirest_firstapp.domain.usuario.Usuario;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("consultas")
public class ConsultaController {

    @Transactional
    @PostMapping
    public ResponseEntity reservar(@RequestBody @Valid DatosReservaConsulta datos) {
        return ResponseEntity.ok(new DatosDelleConsulta(null, null, null, null));
    }
}
