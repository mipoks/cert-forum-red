package design.kfu.sunrise.service;

import design.kfu.sunrise.domain.model.Category;
import design.kfu.sunrise.domain.model.Club;
import design.kfu.sunrise.domain.model.Comment;
import design.kfu.sunrise.domain.model.util.Review;
import design.kfu.sunrise.domain.model.util.ReviewResult;
import design.kfu.sunrise.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Set;

/**
 * @author Daniyar Zakiev
 */
@Service
public class ReviewServiceImpl implements ReviewService {

    @Autowired
    private CommentRepository commentRepository;

    @Override
    public void addCommentForReview(Comment comment) {

    }

    @Override
    public void addCategoryForReview(Category category) {

    }

    @Override
    public void addClubForReview(Club club) {

    }

    @Override
    public Set<Review> findReviews(Pageable pageable) {
        return null;
    }

    @Override
    public boolean reviewComment(Comment comment, ReviewResult reviewResult) {
        return false;
    }

    @Override
    public boolean reviewCategory(Category category, ReviewResult reviewResult) {
        return false;
    }

    @Override
    public boolean reviewClub(Club club, ReviewResult reviewResult) {
        return false;
    }
}
