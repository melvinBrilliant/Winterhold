package melvin.mvc.winterhold.dto.book;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookByCateogryDto {
    private String categoryName; // categoryName = id table Category
    private String bookCode; // bookCode = id table Book
    private Long authorId;
    private String bookTitle;
    private String authorName; // title + firstName + lastName
    private Boolean isBorrowed;
    private LocalDate releaseDate;
    private Integer totalPage;
}
