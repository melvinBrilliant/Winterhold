package melvin.mvc.winterhold.controller;

import melvin.mvc.winterhold.dto.account.RegisterDto;
import melvin.mvc.winterhold.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
@RequestMapping("account")
public class AccountController {

    @Autowired
    private AccountService accountService;

    @GetMapping("login-form")
    public String loginForm() {
        return "account/login-form"; // todo: buat html login-form
    }

    @GetMapping("access-denied")
    public String accessDenied() {
        return "account/access-denied";
    }

    @GetMapping("register-form")
    public String registerForm(Model model) {
        RegisterDto registerDto = new RegisterDto();
        model.addAttribute("account", registerDto);
        return "account/register-form";
    }

    @PostMapping("register")
    public String register(@Valid @ModelAttribute("account") RegisterDto registerDto,
                           BindingResult bindingResult,
                           RedirectAttributes redirectAttributes,
                           Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("account", registerDto);
            return "account/register-form";
        } else {
            accountService.registerAccount(registerDto);
            redirectAttributes.addFlashAttribute("SUCCESS",
                    "ACCOUNT HAS BEEN CREATED");
        }

        return "redirect:/account/login-form";
    }
}
