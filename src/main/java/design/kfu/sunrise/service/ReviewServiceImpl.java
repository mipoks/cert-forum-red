package design.kfu.sunrise.service;

import design.kfu.sunrise.domain.event.ReviewEvent;
import design.kfu.sunrise.domain.model.Category;
import design.kfu.sunrise.domain.model.Club;
import design.kfu.sunrise.domain.model.Comment;
import design.kfu.sunrise.domain.model.util.Review;
import design.kfu.sunrise.repository.CommentRepository;
import design.kfu.sunrise.repository.util.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

/**
 * @author Daniyar Zakiev
 */
@Service
public class ReviewServiceImpl implements ReviewService {

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private ReviewRepository reviewRepository;

    @Override
    public void addCommentForReview(Comment comment) {
        Review review = Review.builder()
                .name()
                .description()
                .instant()
                .object()
                .objectId()
                .build();
        reviewRepository.save(review);
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
    @Transactional
    public boolean reviewComment(Comment comment, Review review) {
        Comment commentLocked = commentRepository.findByIdWithLock(comment.getId()).get();
        commentLocked.getCommentInfo().setVisible(review.isAccept());
        commentRepository.save(commentLocked);
        reviewRepository.save(review);

        new ReviewEvent(Comment.class.getName(), )
        //ToDo создать eventh
        return true;
    }

    @Override
    public boolean reviewCategory(Category category, Review review) {
        return false;
    }

    @Override
    public boolean reviewClub(Club club, Review review) {
        return false;
    }
}
