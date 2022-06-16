package melvin.mvc.winterhold.service.loan;

import melvin.mvc.winterhold.dao.LoanRepository;
import melvin.mvc.winterhold.dto.loan.LoanGridDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class LoanService {
    @Autowired
    private LoanRepository loanRepository;
    private final int PAGE_LIMIT = 5;

    public Page<LoanGridDto> findAllLoans(Integer page, String bookTitle, String customerName) {
        Pageable pageable = PageRequest.of(page, PAGE_LIMIT, Sort.by("id"));
        return loanRepository.findAll(bookTitle, customerName, pageable);
    }
}
