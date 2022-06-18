package melvin.mvc.winterhold.dto.customer;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpsertCustomerDto implements Serializable {
    @NotBlank(message = "Membership number can not be empty")
    private String membershipNumber; // membershipNumber = id
    @NotBlank(message = "First name can not be empty")
    private String firstName;
    private String lastName;
    @NotNull(message = "Birth date can not be empty")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate birthDate;
    @NotBlank(message = "Choose one gender!")
    private String gender;
    private String phone;
    private String address;
}
