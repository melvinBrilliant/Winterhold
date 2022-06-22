package melvin.mvc.winterhold.dao;

import melvin.mvc.winterhold.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account, String> {
}