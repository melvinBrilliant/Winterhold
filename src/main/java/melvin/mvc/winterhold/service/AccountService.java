package melvin.mvc.winterhold.service;

import melvin.mvc.winterhold.config.AppSecurityConfig;
import melvin.mvc.winterhold.dao.AccountRepository;
import melvin.mvc.winterhold.dto.account.RegisterDto;
import melvin.mvc.winterhold.model.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AccountService {
    @Autowired
    private AccountRepository accountRepository;

    public void registerAccount(RegisterDto registerDto) {
        PasswordEncoder passwordEncoder = AppSecurityConfig.passwordEncoder();
        String hashPassword = passwordEncoder.encode(registerDto.getPassword());

        accountRepository.save(
                new Account(
                        registerDto.getId(),
                        hashPassword
                )
        );
    }
}
