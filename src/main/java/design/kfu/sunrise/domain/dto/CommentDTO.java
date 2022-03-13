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

    private String value;
    private Account account;
    private Club club;

    public static Comment toComment(CommentDTO commentDTO) {
        return Comment.builder()
                .value(commentDTO.getValue())
                .account(commentDTO.getAccount())
                .club(commentDTO.getClub())
                .build();
    }
}
