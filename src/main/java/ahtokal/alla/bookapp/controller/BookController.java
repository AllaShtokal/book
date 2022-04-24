package ahtokal.alla.bookapp.controller;

import ahtokal.alla.bookapp.dto.BookDTO;
import ahtokal.alla.bookapp.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RestController
@RequiredArgsConstructor
@CrossOrigin
@RequestMapping("/api/book")
public class BookController {

    private final BookService bookService;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<BookDTO>> getAll( Pageable pageable) {
        pageable = Objects.isNull(pageable) ? Pageable.unpaged() : pageable;
        Page<BookDTO> page = bookService.findAll(pageable);
        return ResponseEntity.ok(page.getContent());
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<BookDTO> create(@RequestBody BookDTO dto){
        BookDTO result = bookService.save(dto);
        return ResponseEntity.ok(result);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        bookService.delete(id);
        return ResponseEntity.ok().build();
    }
}
