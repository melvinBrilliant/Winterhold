package melvin.mvc.winterhold.dto.loan;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpsertLoanDto implements Serializable {
    private Long loanId;
    @NotBlank(message = "Customer can not be empty")
    private String customerNumberId;
    @NotBlank(message = "Book can not be empty")
    private String bookCodeId;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @NotNull(message = "Loan date can not be empty")
    private LocalDate loanDate;
    private String note;
}
