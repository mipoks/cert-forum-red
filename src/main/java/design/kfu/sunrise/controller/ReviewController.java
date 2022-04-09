package design.kfu.sunrise.controller;

import design.kfu.sunrise.domain.model.Account;
import design.kfu.sunrise.domain.model.Category;
import design.kfu.sunrise.domain.model.Club;
import design.kfu.sunrise.domain.model.Comment;
import design.kfu.sunrise.domain.model.util.Review;
import design.kfu.sunrise.service.CategoryService;
import design.kfu.sunrise.service.ClubService;
import design.kfu.sunrise.service.CommentService;
import design.kfu.sunrise.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * @author Daniyar Zakiev
 */
@RestController
@RequestMapping(value = "v1")
public class ReviewController {

    @Autowired
    private ReviewService reviewService;

    @Autowired
    private CommentService commentService;

    @Autowired
    private ClubService clubService;

    @Autowired
    private CategoryService categoryService;


    @PreAuthorize("hasRole('ADMIN') || hasRole('PARTNER')")
    @GetMapping("/reviews")
    public Page<Review> getReviews(@RequestParam(value = "of", defaultValue = "all") String all, @RequestParam(value = "size", defaultValue = "20") int size, @RequestParam(value = "page", defaultValue = "0") int page) {
        Pageable pageable = PageRequest.of(page, size);
        return switch (all) {
            case "comment" -> reviewService.findReviewsForComments(pageable);
            case "club" -> reviewService.findReviewsForClubs(pageable);
            case "category" -> reviewService.findReviewsForCategories(pageable);
            default -> reviewService.findReviews(pageable);
        };
    }


    @PreAuthorize("@access.hasAccessToMakeReview(#account, #review, \"comment\")")
    @PutMapping("/comment/review/{review_id}")
    public boolean makeReviewComment(@Valid @RequestBody Review reviewResult, @PathVariable("review_id") Review review, @AuthenticationPrincipal(expression = "account") Account account) {
        review.setAccept(reviewResult.isAccept());
        review.setReason(review.getReason());

        Comment comment = commentService.findOrThrow(review.getObjectId());
        return reviewService.reviewComment(comment, review);
    }

    @PreAuthorize("@access.hasAccessToMakeReview(#account, #review, \"club\")")
    @PutMapping("/club/review/{review_id}")
    public boolean makeReviewClub(@Valid @RequestBody Review reviewResult, @PathVariable("review_id") Review review, @AuthenticationPrincipal(expression = "account") Account account) {
        review.setAccept(reviewResult.isAccept());
        review.setReason(review.getReason());

        Club club = clubService.findOrThrow(review.getObjectId());
        return reviewService.reviewClub(club, review);
    }

    @PreAuthorize("@access.hasAccessToMakeReview(#account, #review, \"category\")")
    @PutMapping("/category/review/{review_id}")
    public boolean makeReviewCategory(@Valid @RequestBody Review reviewResult, @PathVariable("review_id") Review review, @AuthenticationPrincipal(expression = "account") Account account) {
        review.setAccept(reviewResult.isAccept());
        review.setReason(review.getReason());

        Category category = categoryService.findOrThrow(review.getObjectId());
        return reviewService.reviewCategory(category, review);
    }
}
