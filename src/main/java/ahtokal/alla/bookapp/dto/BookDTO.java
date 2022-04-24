package ahtokal.alla.bookapp.dto;

import lombok.Builder;
import lombok.Data;
import lombok.experimental.FieldNameConstants;

@Data
@Builder
@FieldNameConstants
public class BookDTO {
    private Long id;
    private String author;
    private String title;
    private String code;

}
