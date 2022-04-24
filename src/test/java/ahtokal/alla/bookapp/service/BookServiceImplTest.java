package ahtokal.alla.bookapp.service;

import ahtokal.alla.bookapp.dto.BookDTO;
import ahtokal.alla.bookapp.model.Book;
import ahtokal.alla.bookapp.repository.BookRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;

@SpringBootTest
class BookServiceImplTest {

    @Autowired
    BookServiceImpl bookServiceImple;

    @MockBean
    BookRepository bookRepo;

    @BeforeEach
    void setUp() {
        Book mockBook = Book.builder()
                .id(1L)
                .author("Author")
                .title("Title")
                .isbn("0-201-63385-X")
                .build();

        Mockito.when(bookRepo.save(any(Book.class))).thenReturn(mockBook);
    }

    @Test
    void save() {
        BookDTO expected = BookDTO.builder()
                .id(1L)
                .author("Author")
                .title("Title")
                .isbn("0-201-63385-X")
                .build();

        BookDTO actual = bookServiceImple.save(BookDTO.builder()
                .id(1L)
                .author("Author")
                .title("Title")
                .isbn("0-201-63385-X")
                .build());

        assertEquals(expected.getId(), actual.getId());
        assertEquals(expected.getAuthor(), actual.getAuthor());
        assertEquals(expected.getTitle(), actual.getTitle());
        assertEquals(expected.getIsbn(), actual.getIsbn());
    }

}