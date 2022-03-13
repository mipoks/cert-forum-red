package design.kfu.sunrise.domain.model;


import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class Authority {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private AuthorityType authorityType;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "authorities_accounts",
            joinColumns = @JoinColumn(name = "authority_id"),
            inverseJoinColumns = @JoinColumn(name = "accounts_id"))
    private List<Account> accounts = new ArrayList<>();

    @ManyToMany(mappedBy = "authorities", cascade = CascadeType.ALL)
    private List<Club> clubs = new ArrayList<>();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Authority that = (Authority) o;
        return id != null && Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }

    public enum AuthorityType {
        READ_CLUB_COMMENTS, WRITE_CLUB_COMMENTS
    }
}
