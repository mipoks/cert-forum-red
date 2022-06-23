package design.kfu.sunrise.domain.model.embedded;

import lombok.*;

import javax.persistence.Embeddable;
import javax.persistence.PrePersist;
import java.io.Serializable;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class AccountInfo implements Serializable {
    @Getter
    @Setter
    private String phone;
    @Getter
    private boolean emailConfirmed;

    @PrePersist
    public void setEmailConfirmed() {
        this.emailConfirmed = false;
    }

    public void setEmailConfirmed(boolean confirmed) {
        this.emailConfirmed = confirmed;
    }
}
