package co.edu.uniquindio.clinicaX.infra.errors;

import co.edu.uniquindio.clinicaX.dto.security.MensajeDTO;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.ValidationException;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalException {
    //va desde lo particular a lo general
    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<MensajeDTO<String>> badCredentialsException(BadCredentialsException e){
        return ResponseEntity.internalServerError().body( new MensajeDTO<>(true, "Los datos son incorrectos")
        );
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<MensajeDTO<String>> generalException(Exception e){
        return ResponseEntity.internalServerError().body( new MensajeDTO<>(true, e.getMessage())
        );
    }
    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity tratarError404(){
        return ResponseEntity.notFound().build();
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<MensajeDTO> tratarError400(MethodArgumentNotValidException e){
        //BindingResult results = e.getBindingResult();
        var errors = e.getFieldErrors().stream().map(ValidacionDTO::new).toList();
        return ResponseEntity.badRequest().body(new MensajeDTO<>(true,errors));
    }


    @ExceptionHandler(ValidacionDeIntegridadE.class)
    public ResponseEntity ErrorHandlerValidacionesDeIntegridad(Exception e){
        return ResponseEntity.badRequest().body(e.getMessage());
    }

    @ExceptionHandler(ValidationException.class)
    public ResponseEntity ErrorHandlerValidacionesDeNegocio(Exception e){
        return ResponseEntity.badRequest().body(e.getMessage());
    }

    private record ValidacionDTO(String campo, String mensaje){
        public ValidacionDTO(FieldError error){
            this(error.getField(), error.getDefaultMessage());
        }
    }
}
