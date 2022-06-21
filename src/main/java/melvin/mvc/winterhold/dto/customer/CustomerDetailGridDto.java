package melvin.mvc.winterhold.dto.customer;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CustomerDetailGridDto {
    private String membershipNumber; // id table Customer
    private String fullName;
    private String birthDate;
    private String gender;
    private String phone;
    private String address;
}
