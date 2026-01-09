package alvino.dev.apirest_firstapp.controller;

import alvino.dev.apirest_firstapp.domain.consulta.DatosDeConsulta;
import alvino.dev.apirest_firstapp.domain.consulta.DatosReservaConsulta;
import alvino.dev.apirest_firstapp.domain.consulta.ReservaDeConsultas;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("consultas")
public class ConsultaController {
    @Autowired
    private ReservaDeConsultas reservaDeConsultas;

    @Transactional
    @PostMapping
    public ResponseEntity reservar(@RequestBody @Valid DatosReservaConsulta datos) {

        reservaDeConsultas.reservar(datos);

        return ResponseEntity.ok(new DatosDeConsulta(null, null, null, null));
    }
}
