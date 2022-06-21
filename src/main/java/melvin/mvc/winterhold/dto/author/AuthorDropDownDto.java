package melvin.mvc.winterhold.dto.author;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import melvin.mvc.winterhold.model.Author;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AuthorDropDownDto {
    private Long id; // id table Author
    private String fullName;

    public static List<AuthorDropDownDto> toList(List<Author> authors) {
        List<AuthorDropDownDto> result = new ArrayList<>();

        for (Author author :
                authors) {
            result.add(new AuthorDropDownDto(
                    author.getId(),
                    author.fetchFullName()
            ));
        }
        return result;
    }
}
