package melvin.mvc.winterhold.service.category;

import melvin.mvc.winterhold.dao.AuthorRepository;
import melvin.mvc.winterhold.dao.BookRepository;
import melvin.mvc.winterhold.dao.CategoryRepository;
import melvin.mvc.winterhold.dto.author.AuthorDropDownDto;
import melvin.mvc.winterhold.dto.book.BookByCateogryDto;
import melvin.mvc.winterhold.dto.book.UpsertBookDto;
import melvin.mvc.winterhold.dto.category.CategoryDto;
import melvin.mvc.winterhold.dto.category.CategoryGridDto;
import melvin.mvc.winterhold.dto.category.CategoryUpsertDto;
import melvin.mvc.winterhold.model.Author;
import melvin.mvc.winterhold.model.Book;
import melvin.mvc.winterhold.model.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService implements ICategoryService{

    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private AuthorRepository authorRepository;
    private final int PAGE_LIMIT = 5;

    @Override
    public Page<CategoryDto> findAllCategory(Integer page, String categoryName) {
        Pageable pageable = PageRequest.of(page - 1, PAGE_LIMIT, Sort.by("id"));
        return categoryRepository.findAll(categoryName, pageable);
    }

    @Override
    public void saveCategory(CategoryUpsertDto categoryDto) {
        Category category = Category.builder()
                .id(categoryDto.getCategoryName())
                .floor(categoryDto.getFloor())
                .isle(categoryDto.getIsle())
                .bay(categoryDto.getBay())
                .build();
        categoryRepository.save(category);
    }

    @Override
    public CategoryGridDto findCategoryById(String categoryName) {
        Category category = categoryRepository.findById(categoryName).
                orElseThrow(() -> new RuntimeException("Category not found"));
        return new CategoryGridDto(
                category.getId(),
                category.getFloor(),
                category.getIsle(),
                category.getBay(),
                category.getBooks().size()
        );
    }

    @Override
    public void deleteCategoryById(String categoryName) {
        categoryRepository.deleteById(categoryName);
    }

    @Override
    public Page<BookByCateogryDto> findBookByCategory(String categoryName, String bookTitle, String authorName, Integer page) {
        Pageable pageable = PageRequest.of(page - 1, PAGE_LIMIT, Sort.by("id"));
        return bookRepository.findAllBooksInCategory(categoryName, bookTitle, authorName, pageable);
    }

    public UpsertBookDto findBookById(String bookId) {
        Book book = bookRepository.findById(bookId)
                .orElseThrow(() -> new RuntimeException("Book not found"));

        return new UpsertBookDto(
                book.getId(),
                book.getTitle(),
                book.getCategoryName().getId(),
                book.getAuthor().getId(),
                book.getReleaseDate(),
                book.getTotalPage(),
                book.getSummary()
        );
    }

    public void saveBook(UpsertBookDto bookDto) {
        Category category = categoryRepository.findById(bookDto.getCategoryName())
                .orElseThrow(() -> new RuntimeException("Cateogry not found"));

        Author author = authorRepository.findById(bookDto.getAuthorId())
                .orElseThrow(() -> new RuntimeException("Author not found"));

        Book book = Book.builder()
                .id(bookDto.getCode())
                .title(bookDto.getTitle())
                .categoryName(category)
                .author(author)
                .isBorrowed(false)
                .summary(bookDto.getSummary())
                .releaseDate(bookDto.getReleaseDate())
                .totalPage(bookDto.getTotalPage())
                .build();
        bookRepository.save(book);
    }

    public void deleteBookById(String code) { // code = id table Book
        bookRepository.deleteById(code);
    }

    public List<AuthorDropDownDto> findAllAuthors() {
        List<Author> authors = authorRepository.findAll();
        return AuthorDropDownDto.toList(authors);
    }
}
