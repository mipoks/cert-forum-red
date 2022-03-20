package design.kfu.sunrise.domain.dto;

import design.kfu.sunrise.domain.model.Account;
import design.kfu.sunrise.domain.model.Club;
import design.kfu.sunrise.domain.model.Comment;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CommentDTO {

    private Long id;
    private String value;
    private Long accountId;
    private Long clubId;

    public static Comment toComment(CommentDTO commentDTO, Club club, Account account) {
        return Comment.builder()
                .value(commentDTO.getValue())
                .account(account)
                .club(club)
                .build();
    }

    public static CommentDTO fromComment(Comment comment) {
        return CommentDTO.builder()
                .value(comment.getValue())
                .id(comment.getId())
                .accountId(comment.getAccount().getId())
                .clubId(comment.getClub().getId())
                .build();
    }
}
