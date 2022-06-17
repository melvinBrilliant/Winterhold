package melvin.mvc.winterhold.dto.author;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpsertAuthorDto implements Serializable {
    private Long id;
    @NotBlank(message = "Title can not be empty")
    private String title;
    @NotBlank(message = "First Name can not be empty")
    private String firstName;
    private String lastName;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @NotNull(message = "Birth Date can not be empty")
    private LocalDate birthDate;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate deceasedDate;
    private String education;
    private String summary;
}
