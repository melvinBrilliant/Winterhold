package melvin.mvc.winterhold.service.author;

import melvin.mvc.winterhold.dto.author.AuthorDto;
import melvin.mvc.winterhold.dto.author.UpsertAuthorDto;
import melvin.mvc.winterhold.dto.book.BookByAuthorDetailsDto;
import org.springframework.data.domain.Page;

public interface IAuthorService {
    Page<AuthorDto> findAllAuthor(Integer page, String fullName);
    void saveAuthor(UpsertAuthorDto authorDto);
    UpsertAuthorDto findAuthorById(Long id);
    void deleteAuthorById(Long id);
    Page<BookByAuthorDetailsDto> findBooksByAuthor(Long authorId, Integer page);
}
