package design.kfu.sunrise.controller;

import design.kfu.sunrise.domain.model.Account;
import design.kfu.sunrise.domain.model.util.Review;
import design.kfu.sunrise.domain.model.util.ReviewResult;
import design.kfu.sunrise.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
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
    public Set<Review> getReviews(@RequestParam(value = "size", defaultValue = "20") int size, @RequestParam(value = "page", defaultValue = "0") int page) {
        Pageable pageable = PageRequest.of(page, size);
        return reviewService.findReviews(pageable);
    }

    @PreAuthorize("@access.hasAccessToMakeReview(#account, #review)")
    @PutMapping("/review/{review_id}")
    public boolean markReview(@Valid @RequestBody ReviewResult reviewResult, @PathVariable("review_id") Review review, @AuthenticationPrincipal(expression = "account") Account account) {
        return reviewService.processReview(review, reviewResult);
    }
}
