package design.kfu.sunrise.domain.model;


import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@Table(name = "club_accounts")
public class Authority {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;

    @EmbeddedId
    private ClubsInfo clubsInfo;

    @ElementCollection(targetClass=AuthorityType.class, fetch = FetchType.EAGER)
    @Enumerated(EnumType.STRING)
    @CollectionTable(name="authority_type")
    private Set<AuthorityType> authorityTypes = new HashSet<>();


    public Authority addAuthotityType(AuthorityType type) {
        authorityTypes.add(type);
        return this;
    }

    public Authority removeAuthorityType(AuthorityType type) {
        authorityTypes.remove(type);
        return this;
    }

//    @ManyToMany(cascade = CascadeType.ALL)
//    @JoinTable(name = "authorities_accounts",
//            joinColumns = @JoinColumn(name = "authority_id"),
//            inverseJoinColumns = @JoinColumn(name = "accounts_id"))
//    private Set<Account> accounts = new HashSet<>();

//    @ManyToMany(mappedBy = "authorities", cascade = CascadeType.ALL)
//    private Set<Club> clubs = new HashSet<>();

//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
//        Authority that = (Authority) o;
//        return id != null && Objects.equals(id, that.id);
//    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }

    public enum AuthorityType {
        READ_CLUB_COMMENTS, WRITE_CLUB_COMMENTS
    }
}
