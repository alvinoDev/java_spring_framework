package alvino.dev.apirest_firstapp.infra.security;

import alvino.dev.apirest_firstapp.domain.usuario.Usuario;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;

@Service
public class TokenService {
    public String generarToken(Usuario usuario) {
        try {
            var algorithm = Algorithm.HMAC256("123456789");
            return JWT.create()
                    .withIssuer("API First App")
                    .withSubject(usuario.getUsername())
                    .withExpiresAt(fechaExpiracion())
                    .sign(algorithm);
        } catch (JWTCreationException exception){
            throw new RuntimeException("ERROR al generar el TOKEN", exception);
        }
    }

    private Instant fechaExpiracion() {
        return LocalDateTime.now().plusHours(1).toInstant(ZoneOffset.of("-04:00"));
    }
}
