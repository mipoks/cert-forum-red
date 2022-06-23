package design.kfu.sunrise.domain.model.embedded;

import lombok.*;

import javax.persistence.Embeddable;
import javax.persistence.PrePersist;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class ClubInfo implements Serializable {

    private boolean expired;
    private boolean visible;
    //ToDo сделать проверку на условие
    @NotNull
    private String expireCondition;

    //Различные варианты: истечение по количеству участников, истечение по времени
    public boolean isExpired() {
        return expired;
    }

    @PrePersist
    public void expiredFalse() {
        this.visible = false;
        this.expired = false;
    }
}
