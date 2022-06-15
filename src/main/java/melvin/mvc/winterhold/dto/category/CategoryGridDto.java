package melvin.mvc.winterhold.dto.category;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CategoryGridDto implements Serializable {
    private String categoryName; // categoryName adalah id category
    private Integer floor;
    private String isle;
    private String bay;
    private Integer totalBooks;
}
