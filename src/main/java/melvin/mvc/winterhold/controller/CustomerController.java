package melvin.mvc.winterhold.controller;

import melvin.mvc.winterhold.dto.customer.CustomerGridDto;
import melvin.mvc.winterhold.dto.customer.UpsertCustomerDto;
import melvin.mvc.winterhold.service.customer.CustomerService;
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
        model.addAttribute("membershipNumber", membershipNumber);
        model.addAttribute("customerName", customerName);
        model.addAttribute("page", page);
        model.addAttribute("totalPage", allCustomers.getTotalPages());
        model.addAttribute("breadCrumbs", "CUSTOMER / INDEX");
        return "customer/customer-index";
    }

    @GetMapping("upsert-form")
    public String upsertForm(@RequestParam(required = false) String membershipNumber, Model model) {
        if (membershipNumber != null) { // membershipNumber = id
            model.addAttribute("customer", service.findCustomerById(membershipNumber));
            model.addAttribute("breadCrumbs", "CUSTOMER / UPDATE CUSTOMER");
        } else {
            model.addAttribute("customer", new UpsertCustomerDto());
            model.addAttribute("breadCrumbs", "CUSTOMER / INSERT NEW CUSTOMER");
        }
        return "customer/customer-form";
    }

    @PostMapping("upsert")
    public String upsert(@Valid @ModelAttribute("customer") UpsertCustomerDto customer,
                         BindingResult bindingResult,
                         RedirectAttributes redirectAttributes,
                         Model model) {
        if (bindingResult.hasErrors()) {
            if (bindingResult.getRawFieldValue("membershipNumber") == "") {
                model.addAttribute("breadCrumbs", "CUSTOMER / INSERT NEW CUSTOMER");
            } else {
                model.addAttribute("breadCrumbs", "CUSTOMER / UPDATE CUSTOMER");
            }
            model.addAttribute("customer", customer);
            return "customer/customer-form";
        }
        service.saveCustomer(customer);
        redirectAttributes.addFlashAttribute("SUCCESS",
                "Customer's data have been saved");
        return "redirect:/customer/index";
    }

    @GetMapping("delete")
    public String delete(@RequestParam String membershipNumber, RedirectAttributes redirectAttributes) {
        if (membershipNumber != null) { // membershipNumber = id
            try {
                service.deleteCustomerById(membershipNumber);
                redirectAttributes.addFlashAttribute("SUCCESS",
                        "Customer has been deleted");
            } catch (Exception e) {
                redirectAttributes.addFlashAttribute("ERROR",
                        "This customer has not returned the books. Can not delete customer");
            }
        }
        return "redirect:/customer/index";
    }

    @GetMapping("extend")
    public String extend(@RequestParam String membershipNumber, RedirectAttributes redirectAttributes) {
        String customerName = service.findCustomerById(membershipNumber).getFirstName() + "'s";

        if (membershipNumber != null) { // membershipNumber = id
            try {
                service.extendMembership(membershipNumber);
                redirectAttributes.addFlashAttribute("SUCCESS",
                        String.format("Successfully extending %s membership", customerName));
            } catch (Exception e) {
                redirectAttributes.addFlashAttribute("ERROR",
                        String.format("Failed to extend %s membership", customerName));
            }
        }
        return "redirect:/customer/index";
    }

}
