package melvin.mvc.winterhold.model;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.LinkedHashSet;
import java.util.Set;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
public class Category {
    @Id
    @Column(name = "Name", nullable = false, length = 100)
    private String id;

    @Column(name = "Floor", nullable = false)
    private Integer floor;

    @Column(name = "Isle", nullable = false, length = 10)
    private String isle;

    @Column(name = "Bay", nullable = false, length = 10)
    private String bay;

    @OneToMany(mappedBy = "categoryName")
    private Set<Book> books = new LinkedHashSet<>();

}