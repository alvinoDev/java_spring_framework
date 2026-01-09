package alvino.dev.apirest_firstapp.infra.exceptions;

import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.ValidationException;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GestorDeErrores {
    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity error404() {
        return ResponseEntity.notFound().build();
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity error400(MethodArgumentNotValidException e) {
        var errores = e.getFieldErrors();
        return ResponseEntity.badRequest().body(
                errores.stream().map(DatosErrorValidacion::new).toList()
        );
    }

    @ExceptionHandler(ValidationException.class)
    public ResponseEntity errorDeValidacion(ValidationException e) {
        return ResponseEntity.badRequest().body(e.getMessage());
    }

    public record DatosErrorValidacion(String field, String message) {
        public DatosErrorValidacion(FieldError fe) {
            this(fe.getField(), fe.getDefaultMessage());
        }
    }
}
