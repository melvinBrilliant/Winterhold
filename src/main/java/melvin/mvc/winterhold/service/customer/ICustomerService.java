package melvin.mvc.winterhold.service.customer;

import melvin.mvc.winterhold.dto.customer.CustomerGridDto;
import melvin.mvc.winterhold.dto.customer.UpsertCustomerDto;
import org.springframework.data.domain.Page;

public interface ICustomerService {
    Page<CustomerGridDto> findAllCustomer(Integer page, String membershipNumber, String customerName);
    void saveCustomer(UpsertCustomerDto customerDto);
    UpsertCustomerDto findCustomerById(String membershipNumber); // membershipNumber = id
    void deleteCustomerById(String membershipNumber);
    void extendMembership(String membershipNumber);
}
