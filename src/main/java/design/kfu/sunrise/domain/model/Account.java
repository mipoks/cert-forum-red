package design.kfu.sunrise.domain.model;

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
@Table(name = "account")
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String phone;
    private String login;
    private String hashPassword;

    @Enumerated(EnumType.STRING)
    private Role role;
    @ManyToMany(mappedBy = "accounts", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<Club> clubs = new HashSet<>();
    @ManyToMany(mappedBy = "accounts", cascade = CascadeType.ALL)
    private Set<Authority> authorities = new HashSet<>();

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
        USER, ADMIN
    }
}
