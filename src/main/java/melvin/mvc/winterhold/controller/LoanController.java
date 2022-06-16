package melvin.mvc.winterhold.controller;

import melvin.mvc.winterhold.dto.loan.LoanGridDto;
import melvin.mvc.winterhold.service.loan.LoanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

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

}
