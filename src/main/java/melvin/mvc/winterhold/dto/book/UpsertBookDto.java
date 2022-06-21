package melvin.mvc.winterhold.dto.book;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpsertBookDto {
    @NotBlank(message = "Book code can not be empty")
    private String code; // code = id table Book
    @NotBlank(message = "Book title can not be empty")
    private String title;
    private String categoryName; // categoryName = id table category
    @NotNull(message = "Author can not be empty")
    private Long authorId;
    private LocalDate releaseDate;
    private Integer totalPage;
    private String summary;
}
