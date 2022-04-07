package design.kfu.sunrise.service;

import design.kfu.sunrise.domain.model.Category;
import design.kfu.sunrise.domain.model.Club;
import design.kfu.sunrise.domain.model.Comment;

/**
 * @author Daniyar Zakiev
 */
public interface ReviewService {
    void reviewComment(Comment comment);
    void reviewCategory(Category category);
    void reviewClub(Club club);
}
