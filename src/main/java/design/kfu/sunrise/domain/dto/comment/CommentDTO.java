package design.kfu.sunrise.domain.dto.comment;

import design.kfu.sunrise.domain.model.Account;
import design.kfu.sunrise.domain.model.Club;
import design.kfu.sunrise.domain.model.Comment;
import design.kfu.sunrise.domain.model.embedded.CommentInfo;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CommentDTO {

    private Long id;
    @NotNull
    @Size(min = 2, max = 4096)
    private String value;
    private String accountId;
    private Long clubId;
    private Long answered;

    public static Comment toComment(CommentDTO commentDTO, Club club, Account account) {
        return Comment.builder()
                .value(commentDTO.getValue())
                .account(account)
                .club(club)
                .answered(club
                        .getComments()
                        .stream()
                        .filter(comment -> comment.getId().equals(commentDTO.getAnswered()))
                        .findFirst()
                        .orElse(null))
                .commentInfo(
                        CommentInfo.builder()
                                .visible(false)
                                .build()
                )
                .build();
    }

    public static CommentDTO from(Comment comment) {
        return CommentDTO.builder()
                .value(comment.getValue())
                .id(comment.getId())
                .accountId(comment.getAccount().getId())
                .clubId(comment.getClub().getId())
                .answered(comment.getAnswered().getId())
                .build();
    }
}
