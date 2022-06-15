package melvin.mvc.winterhold.dao;

import melvin.mvc.winterhold.dto.category.CategoryGridDto;
import melvin.mvc.winterhold.model.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface CategoryRepository extends JpaRepository<Category, String> {
    @Query("""
            SELECT new melvin.mvc.winterhold.dto.category.CategoryGridDto (
                c.id,
                c.floor,
                c.isle,
                c.bay,
                SIZE(c.books)
            )
            FROM Category c
            WHERE c.id LIKE %:categoryName%
            """)
    Page<CategoryGridDto> findAll(String categoryName, Pageable pageable);
}