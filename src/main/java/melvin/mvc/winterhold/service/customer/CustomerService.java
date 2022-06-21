package melvin.mvc.winterhold.service.customer;

import melvin.mvc.winterhold.dao.CustomerRepository;
import melvin.mvc.winterhold.dto.customer.CustomerDetailGridDto;
import melvin.mvc.winterhold.dto.customer.CustomerGridDto;
import melvin.mvc.winterhold.dto.customer.UpsertCustomerDto;
import melvin.mvc.winterhold.model.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class CustomerService implements ICustomerService{
    @Autowired
    CustomerRepository customerRepository;
    private final int PAGE_LIMIT = 5;

    @Override
    public Page<CustomerGridDto> findAllCustomer(Integer page, String membershipNumber, String customerName) {
        Pageable pageable = PageRequest.of(page - 1, PAGE_LIMIT, Sort.by("id"));
        return customerRepository.findAll(membershipNumber, customerName, pageable);
    }

    @Override
    public void saveCustomer(UpsertCustomerDto customerDto) {
        Customer customer = Customer.builder()
                .id(customerDto.getMembershipNumber())
                .firstName(customerDto.getFirstName())
                .lastName(customerDto.getLastName())
                .birthDate(customerDto.getBirthDate())
                .gender(customerDto.getGender())
                .phone(customerDto.getPhone())
                .address(customerDto.getAddress())
                .membershipExpireDate(LocalDate.now().plusYears(2))
                .build();
        customerRepository.save(customer);
    }

    @Override
    public UpsertCustomerDto findCustomerById(String membershipNumber) {
        Customer customer = customerRepository.findById(membershipNumber)
                .orElseThrow(() -> new RuntimeException("Customer not found"));
        return new UpsertCustomerDto(
                customer.getId(),
                customer.getFirstName(),
                customer.getLastName(),
                customer.getBirthDate(),
                customer.getGender(),
                customer.getPhone(),
                customer.getAddress()
        );
    }

    @Override
    public void deleteCustomerById(String membershipNumber) {
        customerRepository.deleteById(membershipNumber);
    }

    @Override
    public void extendMembership(String membershipNumber) {
        Customer customer = customerRepository.findById(membershipNumber)
                .orElseThrow(() -> new RuntimeException("Customer not found"));

        LocalDate newExpireDate = customer.getMembershipExpireDate().plusYears(2);
        customer.setMembershipExpireDate(
                newExpireDate
        );
        customerRepository.save(customer);
    }

    @Override
    public CustomerDetailGridDto showDetailCustomer(String membershipNumber) {
        Customer customer = customerRepository.findById(membershipNumber)
                .orElseThrow(() -> new RuntimeException("Customer not found"));

        return new CustomerDetailGridDto(
                customer.getId(), // membershipNumber
                customer.fetchFullName(),
                customer.birthDateFormatted(),
                customer.getGender(),
                customer.getPhone(),
                customer.getAddress()
        );
    }
}
