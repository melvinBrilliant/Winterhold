package melvin.mvc.winterhold.dto.book;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDate;

@Data
public class BookByAuthorDetailsDto implements Serializable {
    private final String id;
    private final String title;
    private final String categoryName; // categoryName = id table Category
    private final Boolean isBorrowed;
    private final LocalDate releaseDate;
    private final Integer totalPage;
}
