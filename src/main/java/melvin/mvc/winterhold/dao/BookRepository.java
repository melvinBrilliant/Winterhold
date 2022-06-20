package melvin.mvc.winterhold.dao;

import melvin.mvc.winterhold.dto.book.BookByAuthorDetailsDto;
import melvin.mvc.winterhold.dto.book.BookByCateogryDto;
import melvin.mvc.winterhold.model.Book;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface BookRepository extends JpaRepository<Book, String> {
    @Query(value = """
            SELECT new melvin.mvc.winterhold.dto.author.AuthorBookGridDto (
                b.id,
                b.title,
                c.id,
                a.id,
                CONCAT(a.title, '. ', a.firstName, ' ', a.lastName),
                b.isBorrowed,
                b.releaseDate,
                b.totalPage
            )
            FROM Book b
            INNER JOIN b.author AS a
            INNER JOIN b.categoryName AS c
            WHERE a.id = :authorId
            """)
    Page<BookByAuthorDetailsDto> findAllAuthorBooks(Long authorId, Pageable pageable);

    @Query(value = """
            SELECT new melvin.mvc.winterhold.dto.book.BookByCateogryDto (
                c.id,
                b.id,
                a.id,
                b.title,
                CONCAT(a.title, '. ', a.firstName, ' ', a.lastName),
                b.isBorrowed,
                b.releaseDate,
                b.totalPage
            )
            FROM Book b
            INNER JOIN b.categoryName AS c
            INNER JOIN b.author AS a
            WHERE c.id = :categoryName AND
                (b.title LIKE %:bookTitle% OR
                 CONCAT(a.title, '. ', a.firstName, ' ', a.lastName) LIKE %:authorName%)
            """)
    Page<BookByCateogryDto> findAllBooksInCategory(String categoryName,
                                                   String bookTitle,
                                                   String authorName,
                                                   Pageable pageable);
}