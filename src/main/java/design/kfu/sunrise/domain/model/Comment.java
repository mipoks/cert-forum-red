package design.kfu.sunrise.domain.model;

import design.kfu.sunrise.domain.model.embedded.CommentInfo;
import lombok.*;
import org.springframework.util.DigestUtils;

import javax.persistence.*;
import java.nio.charset.StandardCharsets;


@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class Comment extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String value;

    //ToDo дописать
    @ManyToOne
    private Account account;

    //ToDo дописать
    @ManyToOne
    private Comment answered;

    //ToDo дописать
    @OneToOne
    private Club club;

    @Embedded
    private CommentInfo commentInfo;

    public String generateHash() {
        return DigestUtils.md5DigestAsHex((id + ":" + value.hashCode() + ":" + answered.getId() + ":" + club.getId()).getBytes(StandardCharsets.UTF_8));
    }
}
