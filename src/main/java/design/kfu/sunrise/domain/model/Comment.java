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

    @Version
    private Long version;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String value;

    //ToDo дописать
    @ManyToOne(cascade = {CascadeType.DETACH, CascadeType.REFRESH, CascadeType.MERGE}, fetch = FetchType.EAGER)
    private Account account;

    //ToDo дописать
    @ManyToOne(cascade = {CascadeType.DETACH, CascadeType.REFRESH, CascadeType.MERGE}, fetch = FetchType.EAGER)
    private Comment answered;

    //ToDo дописать
    @ManyToOne(cascade = {CascadeType.DETACH, CascadeType.REFRESH, CascadeType.MERGE}, fetch = FetchType.EAGER)
    private Club club;

    @Embedded
    private CommentInfo commentInfo;

    public String generateHash() {
        StringBuilder stringBuilder = new StringBuilder(id.toString());
        stringBuilder.append(value);
        if (answered != null) {
            stringBuilder.append(answered.getId());
        }
        stringBuilder.append(club.getId());

        return DigestUtils.md5DigestAsHex(stringBuilder.toString().getBytes(StandardCharsets.UTF_8));
    }
}
