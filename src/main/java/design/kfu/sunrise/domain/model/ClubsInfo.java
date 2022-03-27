package design.kfu.sunrise.domain.model;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

/**
 * @author Daniyar Zakiev
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Embeddable
public class ClubsInfo implements Serializable {

    @Column(name = "club_id")
    private Long clubId;

    @Column(name = "account_id")
    private Long accountId;

}
