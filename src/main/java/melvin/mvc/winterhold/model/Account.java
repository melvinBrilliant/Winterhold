package melvin.mvc.winterhold.model;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
public class Account {
    @Id
    @Column(name = "Username", nullable = false, length = 20)
    private String id;

    @Column(name = "Password", nullable = false, length = 200)
    private String password;

}