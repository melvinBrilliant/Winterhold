package melvin.mvc.winterhold.dto.category;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CategoryUpsertDto {
    @NotBlank(message = "Category name can not be empty")
    private String categoryName; // categoryName adalah id category
    @NotNull(message = "Floor can not be empty")
    private Integer floor;
    @NotBlank(message = "Isle can not be empty")
    private String isle;
    @NotBlank(message = "Bay can not be empty")
    private String bay;
}
