package melvin.mvc.winterhold.dao;

import melvin.mvc.winterhold.dto.customer.CustomerGridDto;
import melvin.mvc.winterhold.model.Customer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface CustomerRepository extends JpaRepository<Customer, String> {
    @Query("""
            SELECT new melvin.mvc.winterhold.dto.customer.CustomerGridDto (
                c.id,
                CONCAT(c.firstName, ' ', c.lastName),
                c.membershipExpireDate
            )
            FROM Customer c
            WHERE
                CONCAT(c.firstName, ' ', c.lastName) LIKE %:customerName% OR
                c.id LIKE %:membershipNumber%
            """)
    Page<CustomerGridDto> findAll(String membershipNumber, String customerName, Pageable pageable);
}