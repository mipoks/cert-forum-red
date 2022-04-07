package design.kfu.sunrise.service;

import design.kfu.sunrise.domain.model.Category;
import design.kfu.sunrise.domain.model.Club;
import design.kfu.sunrise.domain.model.Comment;
import design.kfu.sunrise.domain.model.util.Review;
import design.kfu.sunrise.domain.model.util.ReviewResult;
import org.springframework.data.domain.Pageable;

import java.util.Set;

/**
 * @author Daniyar Zakiev
 */
public interface ReviewService {
    void reviewComment(Comment comment);
    void reviewCategory(Category category);
    void reviewClub(Club club);
    Set<Review> findReviews(Pageable pageable);
    boolean processReview(Review review, ReviewResult reviewResult);
}
