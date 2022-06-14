package melvin.mvc.winterhold.dto.author;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import melvin.mvc.winterhold.model.Author;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuthorGridDto {
    private String fullName;
    private Integer age;
    private String status;
    private String education;

    public static List<AuthorGridDto> toList(List<AuthorDto> authors) {
        List<AuthorGridDto> result = new ArrayList<>();

        for (AuthorDto author : authors) {
            result.add(new AuthorGridDto(
                    author.fetchFullName(),
                    author.fetchAge(),
                    author.isDeceased(),
                    author.getEducation() == null?
                            "No Information" : author.getEducation()
            ));
        }
        return result;
    }
}
