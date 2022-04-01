package design.kfu.sunrise.domain.model;

import lombok.*;

import javax.persistence.*;


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

//    @Embedded
//    private CommentInfo commentInfo;
}
