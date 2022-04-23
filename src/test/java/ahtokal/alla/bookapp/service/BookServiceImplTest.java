package ahtokal.alla.bookapp.service;

import ahtokal.alla.bookapp.dto.BookDTO;
import ahtokal.alla.bookapp.mapper.BookMapper;
import ahtokal.alla.bookapp.model.Book;
import ahtokal.alla.bookapp.repository.BookRepository;
import ahtokal.alla.bookapp.validator.BookValidator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.mockito.ArgumentMatchers.any;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class BookServiceImplTest {

    @Autowired
    BookServiceImpl bookServiceImple;

    @MockBean
    BookRepository bookRepo;

    @Autowired
    BookValidator bookValidator;

    @Autowired
    BookMapper bookMapper;

    @BeforeEach
    void setUp() {
        Book mockBook = Book.builder()
                .id(1L)
                .author("Author")
                .title("Title")
                .code("Code")
                .build();

        Mockito.when(bookRepo.save(any(Book.class))).thenReturn(mockBook);
    }

    @Test
    void save() {
        BookDTO expected = BookDTO.builder()
                .id(1L)
                .author("Author")
                .title("Title")
                .code("Code")
                .build();

        BookDTO actual = bookServiceImple.save(BookDTO.builder()
                .id(1L)
                .author("Author")
                .title("Title")
                .code("Code")
                .build());

        assertEquals(expected.getId(), actual.getId());
        assertEquals(expected.getAuthor(), actual.getAuthor());
        assertEquals(expected.getTitle(), actual.getTitle());
        assertEquals(expected.getCode(), actual.getCode());
    }

    @Test
    void delete() {
    }

    @Test
    void findAll() {
    }
}