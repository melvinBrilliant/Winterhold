package melvin.mvc.winterhold.service.category;

import melvin.mvc.winterhold.dao.CategoryRepository;
import melvin.mvc.winterhold.dto.category.CategoryGridDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;
    private final int PAGE_LIMIT = 5;

    public Page<CategoryGridDto> findAllCategory(Integer page, String categoryName) {
        Pageable pageable = PageRequest.of(page, PAGE_LIMIT, Sort.by("id"));
        return categoryRepository.findAll(categoryName, pageable);
    }
}
