package ahtokal.alla.bookapp.service;

import ahtokal.alla.bookapp.dto.BookDTO;
import ahtokal.alla.bookapp.mapper.BookMapper;
import ahtokal.alla.bookapp.model.Book;
import ahtokal.alla.bookapp.repository.BookRepository;
import ahtokal.alla.bookapp.validator.BookValidator;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class BookServiceImpl implements BookService {

    private final BookValidator bookValidator;
    private final BookMapper bookMapper;
    private final BookRepository bookRepo;

    public BookServiceImpl(BookValidator bookValidator, BookMapper bookMapper, BookRepository bookRepo) {
        this.bookValidator = bookValidator;
        this.bookMapper = bookMapper;
        this.bookRepo = bookRepo;
    }

    @Override
    @Transactional
    public BookDTO save(final BookDTO dto) {
        bookValidator.checkValid(dto);
        Book bookToSave = bookMapper.mapToEntity(dto);
        Book savedEntity = bookRepo.save(bookToSave);
        return bookMapper.mapToDto(savedEntity);
    }

    @Override
    @Transactional
    public void delete(final Long id) {
        Book entity = bookRepo.findById(id).orElseThrow(() -> new EmptyResultDataAccessException("No entity with id: " + id, 1));
        bookRepo.delete(entity);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<BookDTO> findAll(Pageable pageable) {
        Page<Book> foundPage = bookRepo.findAll(pageable);
        return  foundPage.map(bookMapper::mapToDto);
    }


}
