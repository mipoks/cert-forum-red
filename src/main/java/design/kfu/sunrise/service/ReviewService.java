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
    void addCommentForReview(Comment comment);
    void addCategoryForReview(Category category);
    void addClubForReview(Club club);
    Set<Review> findReviews(Pageable pageable);
    boolean reviewComment(Comment comment, Review review, ReviewResult reviewResult);
    boolean reviewCategory(Category category, ReviewResult reviewResult);
    boolean reviewClub(Club club, ReviewResult reviewResult);
}
