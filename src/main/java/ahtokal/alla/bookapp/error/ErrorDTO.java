package ahtokal.alla.bookapp.error;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor(staticName = "of")
public class ErrorDTO {
    private final String message;
    private final String field;

}

