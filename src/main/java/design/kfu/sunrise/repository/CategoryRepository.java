package design.kfu.sunrise.repository;

import design.kfu.sunrise.domain.model.Account;
import design.kfu.sunrise.domain.model.Category;
import design.kfu.sunrise.domain.model.Club;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
}
