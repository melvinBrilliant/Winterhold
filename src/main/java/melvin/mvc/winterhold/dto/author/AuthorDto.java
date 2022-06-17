package melvin.mvc.winterhold.dto.author;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

@Data
public class AuthorDto implements Serializable {
    private final Long id;
    private final String title;
    private final String firstName;
    private final String lastName;
    private final LocalDate birthDate;
    private final LocalDate deceasedDate;
    private final String education;
    private final String summary;

    public String fetchFullName() {
        return title + ". " +  firstName + " " + lastName;
    }

    public Integer fetchAge() {
        return (Integer) (int) ChronoUnit.YEARS.between(birthDate, LocalDate.now());
    }

    public String isDeceased() {
        if (deceasedDate != null) {
            return "Deceased";
        } else {
            return "Alive";
        }
    }
}
