package design.kfu.sunrise.service;

import design.kfu.sunrise.domain.model.Category;
import design.kfu.sunrise.domain.model.Club;
import design.kfu.sunrise.domain.model.Comment;
import design.kfu.sunrise.domain.model.util.Review;
import org.springframework.data.domain.Pageable;

import java.util.Set;

/**
 * @author Daniyar Zakiev
 */
public interface ReviewService {
    void addCommentForReview(Comment comment);
    void addCategoryForReview(Category category);
    void addClubForReview(Club club);

    Set<Review> findReviewsForComments(Pageable pageable);
    Set<Review> findReviewsForClubs(Pageable pageable);
    Set<Review> findReviewsForCategories(Pageable pageable);
    Set<Review> findReviews(Pageable pageable);

    boolean reviewComment(Comment comment, Review review);
    boolean reviewCategory(Category category, Review review);
    boolean reviewClub(Club club, Review review);
}
