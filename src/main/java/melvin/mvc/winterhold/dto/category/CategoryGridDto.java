package melvin.mvc.winterhold.dto.category;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CategoryGridDto implements Serializable {
    private String categoryName; // categoryName adalah id category
    private Integer floor;
    private String isle;
    private String bay;
    private Integer totalBooks;

    public static List<CategoryGridDto> toList(List<CategoryDto> categories) {
        List<CategoryGridDto> result = new ArrayList<>();

        for (CategoryDto category : categories) {
            result.add(new CategoryGridDto(
                    category.getId(),
                    category.getFloor(),
                    category.getIsle(),
                    category.getBay(),
                    category.getBooks()
            ));
        }
        return result;
    }
}
