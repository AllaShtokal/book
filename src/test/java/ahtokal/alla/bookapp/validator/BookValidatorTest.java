package ahtokal.alla.bookapp.validator;

import ahtokal.alla.bookapp.dto.BookDTO;
import ahtokal.alla.bookapp.exception.BookValidationException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Set;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class BookValidatorTest {

    @Autowired
    BookValidator bookValidator;


    public static List<BookDTO> validData() {
        return List.of(
                BookDTO.builder().author("A uthor").title("Title").isbn("0-201-63385-X").build(),
                BookDTO.builder().author("A uthor").title("Title").isbn("0-201-63385-X").build(),
                BookDTO.builder().author("Author r r r").title("Title").isbn("0-201-63385-X").build(),
                BookDTO.builder().author(" 1 1 1 1 1 Author").title("Title").isbn("0-201-63385-X").build());
    }

    public static List<BookDTO> invalidData() {
        return List.of(
                BookDTO.builder().author("uthor").title("Title").isbn("Code").build(),
                BookDTO.builder().author("uthor 1").title("Title").isbn("Code").build(),
                BookDTO.builder().author("author").title("Title").isbn("Code").build(),
                BookDTO.builder().author("autor").title("Title").isbn("Code").build());
    }

    @MethodSource(value = "validData")
    @ParameterizedTest
    void checkValid(BookDTO bookDTO) {
        Assertions.assertDoesNotThrow(() -> bookValidator.checkValid(bookDTO));
    }

    @MethodSource(value = "invalidData")
    @ParameterizedTest
    void checkInValid(BookDTO bookDTO) {
        Assertions.assertThrows(BookValidationException.class, () -> bookValidator.checkValid(bookDTO));

    }


}