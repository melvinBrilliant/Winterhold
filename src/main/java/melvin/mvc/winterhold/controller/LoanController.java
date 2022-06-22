package melvin.mvc.winterhold.controller;

import melvin.mvc.winterhold.dto.book.BookDropDownDto;
import melvin.mvc.winterhold.dto.customer.CustomerDropDownDto;
import melvin.mvc.winterhold.dto.loan.LoanGridDto;
import melvin.mvc.winterhold.dto.loan.UpsertLoanDto;
import melvin.mvc.winterhold.service.loan.LoanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("loan")
public class LoanController {
    @Autowired
    private LoanService service;

    @GetMapping("index")
    public String index(@RequestParam(defaultValue = "1") int page,
                        @RequestParam(defaultValue = "") String bookTitle,
                        @RequestParam(defaultValue = "") String customerName,
                        Model model) {
        Page<LoanGridDto> allLoans = service.findAllLoans(page, bookTitle, customerName);
        List<LoanGridDto> loanGrid = allLoans.getContent();
        model.addAttribute("loans", loanGrid);
        model.addAttribute("bookTitle", bookTitle);
        model.addAttribute("customerName", customerName);
        model.addAttribute("page", page);
        model.addAttribute("totalPage", allLoans.getTotalPages());
        model.addAttribute("breadCrumbs", "LOAN / INDEX");
        return "loan/loan-index";
    }

    @GetMapping("upsert-form")
    public String uspertForm(@RequestParam(required = false) Long loanId, Model model) {
        List<CustomerDropDownDto> customers = service.findAllCustomer();
        List<BookDropDownDto> books = service.findAllBook();
        model.addAttribute("customers", customers);
        model.addAttribute("books", books);

        if (loanId != null) {
            model.addAttribute("loan", service.findLoanById(loanId));
            model.addAttribute("breadCrumbs", "LOAN / UPDATE LOAN");
        } else {
            model.addAttribute("loan", new UpsertLoanDto());
            model.addAttribute("breadCrumbs", "LOAN / NEW LOAN");
        }
        return "loan/loan-form";
    }

    @PostMapping("upsert")
    public String upsert(@Valid @ModelAttribute("loan") UpsertLoanDto loanDto,
                         BindingResult bindingResult,
                         RedirectAttributes redirectAttributes,
                         Model model) {
        if (bindingResult.hasErrors()) {
            if (bindingResult.getRawFieldValue("loanId") == null) {
                model.addAttribute("breadCrumbs", "LOAN / NEW LOAN");
            } else {
                model.addAttribute("breadCrumbs", "LOAN / UPDATE LOAN");
            }
            model.addAttribute("loan", loanDto);
            return "loan/loan-form";
        }
        service.saveLoan(loanDto);
        redirectAttributes.addFlashAttribute("SUCCESS",
                "Loan data have been saved");
        return "redirect:/loan/index";
    }

    @GetMapping("return")
    public String returnBook(@RequestParam(required = false) Long loanId,
                             RedirectAttributes redirectAttributes,
                             Model model) {
        service.returnBook(loanId);
        redirectAttributes.addFlashAttribute("SUCCESS",
                "Book has been returned");
        return "redirect:/loan/index";
    }

    @GetMapping("delete")
    public String deleteLoan(@RequestParam(required = false) Long loanId,
                             RedirectAttributes redirectAttributes,
                             Model model) {
        service.deleteLoan(loanId);
        redirectAttributes.addFlashAttribute("SUCCESS",
                "Loan has been deleted");
        return "redirect:/loan/index";
    }

}
