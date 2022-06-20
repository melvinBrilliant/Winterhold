package melvin.mvc.winterhold.service.author;

import melvin.mvc.winterhold.dao.AuthorRepository;
import melvin.mvc.winterhold.dao.BookRepository;
import melvin.mvc.winterhold.dto.author.AuthorDto;
import melvin.mvc.winterhold.dto.author.UpsertAuthorDto;
import melvin.mvc.winterhold.dto.book.BookByAuthorDetailsDto;
import melvin.mvc.winterhold.model.Author;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

@Service
public class AuthorService implements IAuthorService{

    @Autowired
    private AuthorRepository authorRepository;

    @Autowired
    private BookRepository bookRepository;
    private final int PAGE_LIMIT = 5;

    @Override
    public Page<AuthorDto> findAllAuthor(Integer page, String fullName){
        Pageable pageable = PageRequest.of(page - 1, PAGE_LIMIT, Sort.by("id"));
        return authorRepository.findAll(fullName, pageable);
    }

    @Override
    public void saveAuthor(UpsertAuthorDto authorDto) {
        Author author = Author.builder()
                .id(authorDto.getId())
                .title(authorDto.getTitle())
                .firstName(authorDto.getFirstName())
                .lastName(authorDto.getLastName())
                .birthDate(authorDto.getBirthDate())
                .deceasedDate(authorDto.getDeceasedDate())
                .education(authorDto.getEducation())
                .summary(authorDto.getSummary())
                .build();
        authorRepository.save(author);
    }

    @Override
    public UpsertAuthorDto findAuthorById(Long id) {
        Author author = authorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Author not found"));
        return new UpsertAuthorDto(
                author.getId(),
                author.getTitle(),
                author.getFirstName(),
                author.getLastName(),
                author.getBirthDate(),
                author.getDeceasedDate(),
                author.getEducation(),
                author.getSummary()
        );
    }

    @Override
    public void deleteAuthorById(Long id) {
        authorRepository.deleteById(id);
    }

    public String dateFormatIndonesia(LocalDate date) {
        DateTimeFormatter formatIndo = DateTimeFormatter.ofPattern(
                "dd MMMM yyyy", new Locale("id", "ID"));
        return formatIndo.format(date);
    }

    @Override
    public Page<BookByAuthorDetailsDto> findBooksByAuthor(Long authorId, Integer page) {
        Pageable pageable = PageRequest.of(page - 1, PAGE_LIMIT, Sort.by("id"));
        return bookRepository.findAllAuthorBooks(authorId, pageable);
    }
}
