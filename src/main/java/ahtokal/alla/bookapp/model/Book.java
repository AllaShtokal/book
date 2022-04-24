package ahtokal.alla.bookapp.model;

import lombok.*;

import javax.persistence.*;

@AllArgsConstructor
@Entity
@Getter
@Builder
@SequenceGenerator(name = Book.SEQ_STORE_NAME, sequenceName = Book.SEQ_NAME, allocationSize = 1)
@Table(name = Book.TABLE_NAME)
@NoArgsConstructor
public class Book {

    public static final String TABLE_NAME = "BOOK";
    public static final String SEQ_NAME = "BOOK_SEQ";
    public static final String SEQ_STORE_NAME = "BOOK_SEQ_STORE";

    public static final String COLUMN_ID = "ID";
    public static final String COLUMN_AUTHOR = "AUTHOR";
    public static final String COLUMN_TITLE = "TITLE";
    public static final String COLUMN_CODE = "CODE";

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = COLUMN_ID)
    private Long id;

    @Column(name = COLUMN_AUTHOR)
    private String author;

    @Column(name = COLUMN_TITLE)
    private String title;

    @Column(name = COLUMN_CODE)
    private String isbn;

}
