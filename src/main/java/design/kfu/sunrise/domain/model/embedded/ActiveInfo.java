package design.kfu.sunrise.domain.model.embedded;

import lombok.*;

import javax.persistence.Embeddable;
import javax.persistence.Transient;
import java.io.Serializable;
import java.time.Instant;
import java.util.Date;

/**
 * @author Daniyar Zakiev
 */
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class ActiveInfo implements Serializable {

    private boolean expired;

    private String expireCondition;

    private Instant created;
    //Различные варианты: истечение по количеству участников, истечение по времени
    public boolean isExpired() {
        return expired;
    }
}
