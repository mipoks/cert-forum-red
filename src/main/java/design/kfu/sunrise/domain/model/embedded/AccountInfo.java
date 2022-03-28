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
public class AccountInfo implements Serializable {
    private Date created;
    private String phone;
}
