package ahtokal.alla.bookapp.validator;

import ahtokal.alla.bookapp.dto.BookDTO;
import ahtokal.alla.bookapp.dto.ErrorDTO;
import ahtokal.alla.bookapp.exception.BookValidationException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@Slf4j
@RequiredArgsConstructor
public class BookValidator {

    private static final String OBJECT_NAME = "bookDTO";
    public static final String AUTHOR_STARTS_WITH = "A";

    public static final String NOT_NULL_MSG = "validate.notNull";
    public static final String STARTS_FROM_A = "validate.startsFromA";

    public void checkValid(BookDTO dto) {
        List<ErrorDTO> errors = new ArrayList<>();
        checkValid(dto, errors);
        check(errors);
    }

    private void checkValid(BookDTO dto, List<ErrorDTO> errors) {
        validateNotNull(errors, BookDTO.Fields.author, dto.getAuthor());
        validateNotNull(errors, BookDTO.Fields.title, dto.getAuthor());
        validateAuthorName(errors, dto.getAuthor());
        validateNotNull(errors, BookDTO.Fields.code, dto.getCode());
        //validate shtrych code
    }

    private void validateNotNull(List<ErrorDTO> errors, String fieldName, Object value) {
        if (value == null) {
            addFieldError(errors, NOT_NULL_MSG, fieldName);
        }
    }

    private void validateAuthorName(List<ErrorDTO> errors, Object value) {
        if (!String.valueOf(value).startsWith(AUTHOR_STARTS_WITH)) {
            addFieldError(errors, STARTS_FROM_A, BookDTO.Fields.author);
        }
    }

    private void addFieldError(List<ErrorDTO> errors, String errorMsg, String fieldName) {
        errors.add(new ErrorDTO(OBJECT_NAME, errorMsg, fieldName));
    }

    private void check(List<ErrorDTO> errors) {
        if (!errors.isEmpty()) {
            log.warn("validation errors={}", errors);
            throw new BookValidationException(errors);
        }
    }

}
