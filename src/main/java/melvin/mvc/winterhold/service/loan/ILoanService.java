package melvin.mvc.winterhold.service.loan;

import melvin.mvc.winterhold.dto.loan.LoanGridDto;
import org.springframework.data.domain.Page;

public interface ILoanService {
    Page<LoanGridDto> findAllLoans(Integer page, String bookTitle, String customerName);
}
