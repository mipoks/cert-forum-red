package design.kfu.sunrise.domain.model.embedded;

import lombok.*;

import javax.persistence.Embeddable;
import javax.persistence.PrePersist;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

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

    //Различные варианты: истечение по количеству участников, истечение по времени
    public boolean isExpired() {
        return expired;
    }

    @PrePersist
    public void expiredFalse() {
        this.expired = false;
    }
}
