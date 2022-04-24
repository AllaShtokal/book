package ahtokal.alla.bookapp.error;

import ahtokal.alla.bookapp.exception.BookValidationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.List;

@Slf4j
@ControllerAdvice
public class ErrorHandler {

    @ExceptionHandler(BookValidationException.class)
    public ResponseEntity<List<ErrorDTO>> processException(BookValidationException ex) {
        log.error(ex.getMessage(), ex);
        return ResponseEntity.badRequest().body(ex.getErrors());
    }
}
