package design.kfu.sunrise.domain.model.embedded;

import lombok.*;

import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.Instant;

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
    //ToDo сделать проверку на условие
    @NotNull
    private String expireCondition;

    private Instant created;
    //Различные варианты: истечение по количеству участников, истечение по времени
    public boolean isExpired() {
        return expired;
    }

    public static ActiveInfo make(ActiveInfo activeInfo) {
        activeInfo.setExpired(false);
        activeInfo.setCreated(Instant.now());
        return activeInfo;
    }
}
