package design.kfu.sunrise.domain.model.embedded;

import lombok.*;

import javax.persistence.Embeddable;
import java.io.Serializable;
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
public abstract class ActiveInfo implements Serializable {
    private Date created;
    //Различные варианты: истечение по количеству участников, истечение по времени
    public abstract boolean isExpired();
    public abstract String expireCondition();
}
