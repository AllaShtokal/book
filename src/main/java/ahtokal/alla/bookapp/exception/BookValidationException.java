package ahtokal.alla.bookapp.exception;

import ahtokal.alla.bookapp.error.ErrorDTO;
import lombok.Getter;

import java.util.List;

@Getter
public class BookValidationException extends RuntimeException {

    private final List<ErrorDTO> errors;

    public BookValidationException(List<ErrorDTO> errors) {
        this.errors = errors;
    }

}
