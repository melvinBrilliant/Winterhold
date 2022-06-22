package melvin.mvc.winterhold.dto.book;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import melvin.mvc.winterhold.model.Book;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookDropDownDto {
    private String bookId;
    private String bookTitle;

    public static BookDropDownDto convert(Book book) {
        return new BookDropDownDto(
                book.getId(),
                book.getTitle()
        );
    }
}
