package melvin.mvc.winterhold.model;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.LinkedHashSet;
import java.util.Set;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
public class Author {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id", nullable = false)
    private Long id;

    @Column(name = "Title", nullable = false, length = 10)
    private String title;

    @Column(name = "FirstName", nullable = false, length = 50)
    private String firstName;

    @Column(name = "LastName", length = 50)
    private String lastName;

    @Column(name = "BirthDate", nullable = false)
    private LocalDate birthDate;

    @Column(name = "DeceasedDate")
    private LocalDate deceasedDate;

    @Column(name = "Education", length = 50)
    private String education;

    @Column(name = "Summary", length = 500)
    private String summary;

    @OneToMany(mappedBy = "author")
    private Set<Book> books = new LinkedHashSet<>();

}