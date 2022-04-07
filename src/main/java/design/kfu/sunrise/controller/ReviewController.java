package design.kfu.sunrise.controller;

import design.kfu.sunrise.domain.model.Account;
import design.kfu.sunrise.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotEmpty;
import java.util.Set;

/**
 * @author Daniyar Zakiev
 */
@RestController(value = "v1")
public class ReviewController {
    //Сделать маппинг для получения объектов для проверки из пула

    @Autowired
    private ReviewService reviewService;

    @PreAuthorize("hasRole('ADMIN') || hasRole('PARTNER')")
    @GetMapping("/reviews")
    public Set<Review> getReviews() {
        return reviewService.findReviews();
    }

    @PutMapping("/review/{review_id}")
    public boolean markReview(@NotEmpty @RequestBody ReviewResult reviewResult, @PathVariable("review_id") Review review, @AuthenticationPrincipal(expression = "account") Account account) {
        return reviewService.processReview(review, reviewResult);
    }
}
