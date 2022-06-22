package melvin.mvc.winterhold.dto.customer;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import melvin.mvc.winterhold.model.Customer;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerDropDownDto {
    private String membershipNumber;
    private String fullName;

    public static CustomerDropDownDto convert(Customer customer) {
        return new CustomerDropDownDto(
                customer.getId(),
                customer.fetchFullName()
        );
    }
}
