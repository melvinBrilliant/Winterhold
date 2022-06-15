package melvin.mvc.winterhold.service.customer;

import melvin.mvc.winterhold.dao.CustomerRepository;
import melvin.mvc.winterhold.dto.category.CategoryGridDto;
import melvin.mvc.winterhold.dto.customer.CustomerGridDto;
import melvin.mvc.winterhold.model.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class CustomerService {
    @Autowired
    CustomerRepository customerRepository;
    private final int PAGE_LIMIT = 5;

    public Page<CustomerGridDto> findAllCustomer(Integer page, String membershipNumber, String customerName) {
        Pageable pageable = PageRequest.of(page, PAGE_LIMIT, Sort.by("id"));
        return customerRepository.findAll(membershipNumber, customerName, pageable);
    }
}
