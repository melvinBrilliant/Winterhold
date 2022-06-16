package melvin.mvc.winterhold.service.category;

import melvin.mvc.winterhold.dto.category.CategoryGridDto;
import org.springframework.data.domain.Page;

public interface ICategoryService {
    Page<CategoryGridDto> findAllCategory(Integer page, String categoryName);
}
