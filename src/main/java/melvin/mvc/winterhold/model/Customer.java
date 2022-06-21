package melvin.mvc.winterhold.model;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.LinkedHashSet;
import java.util.Locale;
import java.util.Set;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
public class Customer {
    @Id
    @Column(name = "MembershipNumber", nullable = false, length = 20)
    private String id;

    @Column(name = "FirstName", nullable = false, length = 50)
    private String firstName;

    @Column(name = "LastName", length = 50)
    private String lastName;

    @Column(name = "BirthDate", nullable = false)
    private LocalDate birthDate;

    @Column(name = "Gender", nullable = false, length = 10)
    private String gender;

    @Column(name = "Phone", length = 20)
    private String phone;

    @Column(name = "Address", length = 500)
    private String address;

    @Column(name = "MembershipExpireDate", nullable = false)
    private LocalDate membershipExpireDate;

    @OneToMany(mappedBy = "customerNumber")
    private Set<Loan> loans = new LinkedHashSet<>();

    public String fetchFullName() {
        return firstName + " " + lastName;
    }

    public String birthDateFormatted() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(
                "MMMM dd yyyy", new Locale("en", "EN")
        );
        return formatter.format(birthDate);
    }

    public String membershipExpireDateFormatted() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(
                "MMMM dd yyyy", new Locale("en", "EN")
        );
        return formatter.format(birthDate);
    }

}