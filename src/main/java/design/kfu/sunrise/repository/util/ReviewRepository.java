package design.kfu.sunrise.repository.util;

import design.kfu.sunrise.domain.model.util.Review;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

import java.util.Set;

/**
 * @author Daniyar Zakiev
 */
public interface ReviewRepository extends CrudRepository<Review, String> {
    Set<Review> findAllByViewedAndOrderByInstant(boolean viewed, Pageable pageable);
    Set<Review> findAllByViewedAndObjectNameOrderByInstant(boolean viewed, String objectName, Pageable pageable);
}
