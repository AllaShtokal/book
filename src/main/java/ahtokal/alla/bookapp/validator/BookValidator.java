package ahtokal.alla.bookapp.validator;

import ahtokal.alla.bookapp.dto.BookDTO;
import ahtokal.alla.bookapp.error.ErrorDTO;
import ahtokal.alla.bookapp.exception.BookValidationException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.validator.routines.ISBNValidator;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Component
@Slf4j
@RequiredArgsConstructor
public class BookValidator {

    public static final String AUTHOR_STARTS_WITH = "A";

    public static final String NOT_NULL_MSG = "Field cannot be empty";
    public static final String STARTS_FROM_A = "Forename or surname should start from letter A";
    public static final String ISBN_INVALID = "ISBN is invalid";

    public void checkValid(BookDTO dto) {
        List<ErrorDTO> errors = new ArrayList<>();
        checkValid(dto, errors);
        check(errors);
    }

    private void checkValid(BookDTO dto, List<ErrorDTO> errors) {
        validateNotNull(errors, BookDTO.Fields.author, dto.getAuthor());
        validateNotNull(errors, BookDTO.Fields.title, dto.getTitle());
        validateAuthorName(errors, dto.getAuthor());
        validateNotNull(errors, BookDTO.Fields.isbn, dto.getIsbn());
        validateIsbn(errors, dto.getIsbn());
    }

    private void validateNotNull(List<ErrorDTO> errors, String fieldName, String value) {
        if (value.isBlank()) {
            addFieldError(errors, NOT_NULL_MSG, fieldName);
        }
    }

    private void validateAuthorName(List<ErrorDTO> errors, String value) {
        List<String> allAuthorNames = Arrays.stream(value.split(" "))
                .map(String::trim)
                .collect(Collectors.toList());

        boolean hasValidName = allAuthorNames.stream()
                .anyMatch(name -> name.startsWith(AUTHOR_STARTS_WITH));

        if (!hasValidName) {
            addFieldError(errors, STARTS_FROM_A, BookDTO.Fields.author);
        }
    }

    private void validateIsbn(List<ErrorDTO> errors, String value) {
        ISBNValidator isbnValidator = new ISBNValidator();
        if (!isbnValidator.isValid(value)) {
            addFieldError(errors, ISBN_INVALID, BookDTO.Fields.isbn);
        }
    }

    private void addFieldError(List<ErrorDTO> errors, String errorMsg, String fieldName) {
        errors.add(ErrorDTO.of(errorMsg, fieldName));
    }

    private void check(List<ErrorDTO> errors){
        if (!errors.isEmpty()) {
            log.warn("validation errors={}", errors);
            throw new BookValidationException(errors);
        }
    }

}
