package ahtokal.alla.bookapp.service;

import ahtokal.alla.bookapp.dto.BookDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface BookService {

    BookDTO save(final BookDTO dto);

    Page<BookDTO> findAll(Pageable pageable);

    void delete(final Long id);
}
