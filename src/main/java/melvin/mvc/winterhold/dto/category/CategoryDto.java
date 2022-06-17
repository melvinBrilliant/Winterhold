package melvin.mvc.winterhold.dto.category;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CategoryDto implements Serializable {
    private String id;
    private Integer floor;
    private String isle;
    private String bay;
    private Integer books;
}
