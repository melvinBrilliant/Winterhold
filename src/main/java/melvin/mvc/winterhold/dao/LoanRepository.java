package melvin.mvc.winterhold.dao;

import melvin.mvc.winterhold.dto.loan.LoanGridDto;
import melvin.mvc.winterhold.model.Loan;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface LoanRepository extends JpaRepository<Loan, Long> {
    @Query("""
            SELECT new melvin.mvc.winterhold.dto.loan.LoanGridDto (
                l.id,
                b.title,
                CONCAT(c.firstName, ' ', c.lastName),
                l.loanDate,
                l.dueDate,
                l.returnDate
            )
            FROM Loan l
            INNER JOIN l.customerNumber AS c
            INNER JOIN l.bookCode AS b
            WHERE CONCAT(c.firstName, ' ', c.lastName) LIKE %:customerName% OR
                b.title LIKE %:bookTitle%
            """)
    Page<LoanGridDto> findAll(String bookTitle, String customerName, Pageable pageable);
}