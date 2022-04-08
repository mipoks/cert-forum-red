package design.kfu.sunrise.repository.util;

import design.kfu.sunrise.domain.model.util.Review;
import org.springframework.data.repository.CrudRepository;

/**
 * @author Daniyar Zakiev
 */
public interface ReviewRepository extends CrudRepository<Review, String> {
}
