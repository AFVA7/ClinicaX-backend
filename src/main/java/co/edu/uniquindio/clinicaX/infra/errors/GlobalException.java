package co.edu.uniquindio.clinicaX.infra.errors;

import co.edu.uniquindio.clinicaX.dto.MensajeDTO;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.ValidationException;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;

@RestControllerAdvice
public class GlobalException {

    //va desde lo particular a lo general

    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<MensajeDTO<String>> badCredentialsException(BadCredentialsException e){
        return ResponseEntity.internalServerError().body( new MensajeDTO<>(true, e.getMessage())
        );
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<MensajeDTO<String>> generalException(Exception e){
        return ResponseEntity.internalServerError().body( new MensajeDTO<>(true, e.getMessage())
        );
    }
    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<MensajeDTO<String>> tratarError404(EntityNotFoundException e){
        return ResponseEntity.notFound().build();
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<MensajeDTO<List<ValidacionDTO>>> tratarError400(MethodArgumentNotValidException e){
        var errors = e.getFieldErrors().stream().map(ValidacionDTO::new).toList();
        return ResponseEntity.badRequest().body(new MensajeDTO<>(true,errors));
    }
    @ExceptionHandler(ValidacionDeIntegridadE.class)
    public ResponseEntity<MensajeDTO<String>> ErrorHandlerValidacionesDeIntegridad(ValidacionDeIntegridadE e){
        return ResponseEntity.badRequest().body(new MensajeDTO<>(true,e.getMessage())
        );
    }

    @ExceptionHandler(ValidationException.class)
    public ResponseEntity<MensajeDTO<String>> handleValidacionesDeNegocio(ValidationException e){
        return ResponseEntity.badRequest().body(new MensajeDTO<>(true, e.getMessage()));
    }

    public record ValidacionDTO(String campo, String mensaje){
        public ValidacionDTO(FieldError error){
            this(error.getField(), error.getDefaultMessage());
        }
    }
}
