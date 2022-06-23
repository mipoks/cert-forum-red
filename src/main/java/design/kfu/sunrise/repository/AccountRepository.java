package design.kfu.sunrise.repository;

import design.kfu.sunrise.domain.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AccountRepository extends JpaRepository<Account, String> {
    Optional<Account> getAccountByEmail(String email);
}
