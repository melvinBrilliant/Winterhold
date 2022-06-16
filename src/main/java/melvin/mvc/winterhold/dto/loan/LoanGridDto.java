package melvin.mvc.winterhold.dto.loan;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDate;

@Data
public class LoanGridDto implements Serializable {
    private final String bookTitle;
    private final String customerName;
    private final LocalDate loanDate;
    private final LocalDate dueDate;
    private final LocalDate returnDate;
}
