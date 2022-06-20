package melvin.mvc.winterhold.service.category;

import melvin.mvc.winterhold.dto.book.BookByCateogryDto;
import melvin.mvc.winterhold.dto.category.CategoryDto;
import melvin.mvc.winterhold.dto.category.CategoryGridDto;
import melvin.mvc.winterhold.dto.category.CategoryUpsertDto;
import org.springframework.data.domain.Page;

public interface ICategoryService {
    Page<CategoryDto> findAllCategory(Integer page, String categoryName);
    void saveCategory(CategoryUpsertDto categoryDto);
    CategoryGridDto findCategoryById(String categoryName); // categoryName adalah id
    void deleteCategoryById(String categoryName);
    Page<BookByCateogryDto> findBookByCategory(String categoryName, String bookTitle, String authorName, Integer page);
}
