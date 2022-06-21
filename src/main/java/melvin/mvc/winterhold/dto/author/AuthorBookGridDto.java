package melvin.mvc.winterhold.dto.author;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuthorBookGridDto implements Serializable {
    private String id;
    private String title;
    private String categoryName; // categoryName = id table Category
    private Long authorId;
    private String authorName;
    private Boolean isBorrowed;
    private LocalDate releaseDate;
    private Integer totalPage;
}