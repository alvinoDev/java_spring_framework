package alvino.dev.apirest_firstapp.controller;

import alvino.dev.apirest_firstapp.domain.usuario.DatosAutenticacion;
import alvino.dev.apirest_firstapp.domain.usuario.Usuario;
import alvino.dev.apirest_firstapp.infra.security.DatosTokenJWT;
import alvino.dev.apirest_firstapp.infra.security.TokenService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class AutenticacionController {
    @Autowired
    private TokenService tokenService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @PostMapping
    public ResponseEntity iniciarSesion(@RequestBody @Valid DatosAutenticacion datos) {
        var token = new UsernamePasswordAuthenticationToken(datos.username(), datos.contrasena());
        var autenticacion =  authenticationManager.authenticate(token);

        var tokenJWT = tokenService.generarToken( (Usuario) autenticacion.getPrincipal() );

        return ResponseEntity.ok(new DatosTokenJWT(tokenJWT));
    }
}
