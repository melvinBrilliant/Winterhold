package melvin.mvc.winterhold.service.loan;

import melvin.mvc.winterhold.dto.loan.LoanGridDto;
import melvin.mvc.winterhold.dto.loan.UpsertLoanDto;
import org.springframework.data.domain.Page;

public interface ILoanService {
    Page<LoanGridDto> findAllLoans(Integer page, String bookTitle, String customerName);
    UpsertLoanDto findLoanById(Long id);
    void saveLoan(UpsertLoanDto loanDto);
    void deleteLoan(Long id);
    void returnBook(Long id);
}
