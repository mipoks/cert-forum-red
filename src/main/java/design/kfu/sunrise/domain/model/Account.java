package design.kfu.sunrise.domain.model;

import design.kfu.sunrise.domain.model.embedded.AccountInfo;
import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(schema = "keycloak", name = "user_entity")
public class Account extends BaseEntity {
    @Id
    private String id;

    @Embedded
    private AccountInfo accountInfo;

    private String email;

    private String firstName;

    private String lastName;

    @ManyToOne
    private Company partnerInfo;


    @ManyToMany(mappedBy = "accounts", cascade = {CascadeType.DETACH, CascadeType.PERSIST, CascadeType.MERGE}, fetch = FetchType.LAZY)
    private Set<Club> clubs = new HashSet<>();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Account account = (Account) o;
        return id != null && Objects.equals(id, account.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }

    public enum Role {
        USER, ADMIN, PARTNER
    }
}
