package ahtokal.alla.bookapp.dto;


import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ErrorDTO {
    private final String objectName;
    private final String message;
    private final String fields;

}

