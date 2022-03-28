package design.kfu.sunrise.domain.model;

import design.kfu.sunrise.domain.model.embedded.CommentInfo;
import lombok.*;

import javax.persistence.*;


@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String value;

    //ToDo дописать
    @ManyToOne
    private Account account;

    //ToDo дописать
    @ManyToOne
    private Comment replayed;

    //ToDo дописать
    @OneToOne
    private Club club;

    @Embedded
    private CommentInfo commentInfo;
}
