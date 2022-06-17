package melvin.mvc.winterhold.service.category;

import melvin.mvc.winterhold.dao.CategoryRepository;
import melvin.mvc.winterhold.dto.category.CategoryDto;
import melvin.mvc.winterhold.dto.category.CategoryGridDto;
import melvin.mvc.winterhold.dto.category.CategoryUpsertDto;
import melvin.mvc.winterhold.model.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class CategoryService implements ICategoryService{

    @Autowired
    private CategoryRepository categoryRepository;
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
}
