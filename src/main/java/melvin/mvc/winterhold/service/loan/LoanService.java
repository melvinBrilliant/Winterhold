package melvin.mvc.winterhold.service.loan;

import melvin.mvc.winterhold.dao.BookRepository;
import melvin.mvc.winterhold.dao.CustomerRepository;
import melvin.mvc.winterhold.dao.LoanRepository;
import melvin.mvc.winterhold.dto.book.BookDropDownDto;
import melvin.mvc.winterhold.dto.customer.CustomerDropDownDto;
import melvin.mvc.winterhold.dto.loan.LoanGridDto;
import melvin.mvc.winterhold.dto.loan.UpsertLoanDto;
import melvin.mvc.winterhold.model.Book;
import melvin.mvc.winterhold.model.Customer;
import melvin.mvc.winterhold.model.Loan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class LoanService implements ILoanService{
    @Autowired
    private LoanRepository loanRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private BookRepository bookRepository;
    private final int PAGE_LIMIT = 5;

    @Override
    public Page<LoanGridDto> findAllLoans(Integer page, String bookTitle, String customerName) {
        Pageable pageable = PageRequest.of(page - 1, PAGE_LIMIT, Sort.by("id"));
        return loanRepository.findAll(bookTitle, customerName, pageable);
    }

    @Override
    public UpsertLoanDto findLoanById(Long id) {
        Loan loan = loanRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Loan not found"));
        return new UpsertLoanDto(
                loan.getId(),
                loan.getCustomerNumber().getId(),
                loan.getBookCode().getId(),
                loan.getLoanDate(),
                loan.getNote()
        );
    }

    @Override
    public void saveLoan(UpsertLoanDto loanDto) {
        Book book = bookRepository.findById(loanDto.getBookCodeId())
                .orElseThrow(() -> new RuntimeException("Book not found"));

        Customer customer = customerRepository.findById(loanDto.getCustomerNumberId())
                .orElseThrow(() -> new RuntimeException("Customer not found"));

        Loan loan = Loan.builder()
                .id(loanDto.getLoanId())
                .customerNumber(customer)
                .bookCode(book)
                .loanDate(loanDto.getLoanDate())
                .dueDate(loanDto.getLoanDate().plusDays(5))
                .note(loanDto.getNote())
                .build();
        loanRepository.save(loan);
    }

    @Override
    public void deleteLoan(Long id) {
        loanRepository.deleteById(id);
    }

    public void returnBook(Long id) {
        Loan loan = loanRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Loan not found"));
        Book book = loan.getBookCode();
        book.setIsBorrowed(false);
        loan.setReturnDate(LocalDate.now());
        bookRepository.save(book);
        loanRepository.save(loan);
    }

    public List<CustomerDropDownDto> findAllCustomer() {
        List<Customer> customers = customerRepository.findAll();
        List<CustomerDropDownDto> result = new ArrayList<>();
        for (Customer customer :
                customers) {
            CustomerDropDownDto customerDropdown = CustomerDropDownDto.convert(customer);
            result.add(customerDropdown);
        }
        return result;
    }

    public List<BookDropDownDto> findAllBook() {
        List<Book> books = bookRepository.findAll();
        List<BookDropDownDto> result = new ArrayList<>();
        for (Book book : books) {
            if (!book.getIsBorrowed()) {
                BookDropDownDto bookDropdown = BookDropDownDto.convert(book);
                result.add(bookDropdown);
            }
        }
        return result;
    }
}
