package melvin.mvc.winterhold.dto.customer;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDate;

@Data
public class CustomerGridDto implements Serializable {
    private final String membershipNumber; //id
    private final String fullName;
    private final LocalDate membershipExpireDate;
}
