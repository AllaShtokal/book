package ahtokal.alla.bookapp.dto;

import lombok.*;
import lombok.experimental.FieldNameConstants;

import java.util.Objects;

@Data
@Builder
@FieldNameConstants
public class BookDTO {
    private Long id;
    private String author;
    private String title;
    private String code;

}
