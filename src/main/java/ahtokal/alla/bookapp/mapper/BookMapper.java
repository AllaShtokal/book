package ahtokal.alla.bookapp.mapper;

import ahtokal.alla.bookapp.dto.BookDTO;
import ahtokal.alla.bookapp.model.Book;
import org.springframework.stereotype.Component;

@Component
public class BookMapper {

    public Book mapToEntity(BookDTO dto) {
        return Book.builder()
                .author(dto.getAuthor())
                .title(dto.getTitle())
                .isbn(dto.getIsbn())
                .build();
    }

    public BookDTO mapToDto(Book entity) {
        return BookDTO.builder()
                .id(entity.getId())
                .author(entity.getAuthor())
                .title(entity.getTitle())
                .isbn(entity.getIsbn())
                .build();
    }

}
