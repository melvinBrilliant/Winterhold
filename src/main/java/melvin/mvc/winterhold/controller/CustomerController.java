package melvin.mvc.winterhold.controller;

import melvin.mvc.winterhold.dto.customer.CustomerGridDto;
import melvin.mvc.winterhold.service.customer.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("customer")
public class CustomerController {
    @Autowired
    private CustomerService service;

    @GetMapping("index")
    public String index(@RequestParam(defaultValue = "1") int page,
                        @RequestParam(defaultValue = "") String membershipNumber,
                        @RequestParam(defaultValue = "") String customerName,
                        Model model) {
        Page<CustomerGridDto> allCustomers = service.findAllCustomer(page, membershipNumber, customerName);
        List<CustomerGridDto> customerGrid = allCustomers.getContent();
        model.addAttribute("customers", customerGrid);
        model.addAttribute("page", page);
        model.addAttribute("totalPage", allCustomers.getTotalPages());
        model.addAttribute("breadCrumbs", "CUSTOMER / INDEX");
        return "customer/customer-index";
    }
}
