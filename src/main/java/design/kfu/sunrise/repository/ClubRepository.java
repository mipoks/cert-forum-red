package design.kfu.sunrise.repository;

import design.kfu.sunrise.domain.model.Account;
import design.kfu.sunrise.domain.model.Club;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface ClubRepository extends JpaRepository<Club, Long> {
    Set<Club> findAllByAuthor(Account account);
}
