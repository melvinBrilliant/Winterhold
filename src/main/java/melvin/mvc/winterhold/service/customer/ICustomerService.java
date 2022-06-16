package melvin.mvc.winterhold.service.customer;

import melvin.mvc.winterhold.dto.customer.CustomerGridDto;
import org.springframework.data.domain.Page;

public interface ICustomerService {
    Page<CustomerGridDto> findAllCustomer(Integer page, String membershipNumber, String customerName);
}
