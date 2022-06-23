package design.kfu.sunrise.domain.model;

import design.kfu.sunrise.domain.model.embedded.ClubInfo;
import design.kfu.sunrise.domain.model.embedded.CostInfo;
import lombok.*;
import org.hibernate.Hibernate;
import org.springframework.util.DigestUtils;

import javax.persistence.*;
import java.nio.charset.StandardCharsets;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
//@NamedEntityGraph(name = "Club.comments",
//        attributeNodes = @NamedAttributeNode("comments")
//)
public class Club extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String description;

    @Embedded
    private CostInfo costInfo;

    @ManyToOne
    private Category category;

    @Embedded
    private ClubInfo clubInfo;

    @ManyToOne
    private Account author;

    @ManyToMany(cascade = {CascadeType.DETACH, CascadeType.PERSIST, CascadeType.REFRESH, CascadeType.MERGE}, fetch = FetchType.EAGER)
    @JoinTable(name = "club_accounts",
            joinColumns = @JoinColumn(name = "club_id"),
            inverseJoinColumns = @JoinColumn(name = "account_id"))
    private Set<Account> accounts = new HashSet<>();
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<Comment> comments = new HashSet<>();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Club club = (Club) o;
        return id != null && Objects.equals(id, club.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }

    public String generateHash() {
        return DigestUtils.md5DigestAsHex((id + ":" + name.hashCode() + ":" + description.hashCode() + ":" + category.getId()).getBytes(StandardCharsets.UTF_8));
    }

//    @Transient
//    private List<ModelEvent<Club>> domainEvents = new ArrayList<>();
//
//    @DomainEvents
//    private List<ModelEvent<Club>> domainEvents() {
//        return Collections.unmodifiableList(this.domainEvents);
//    }
//
//    @PostPersist
//    private void createEventSave() {
//        createDomainEvents();
//        domainEvents.add(new ModelEvent<>(this, "save"));
//    }
//
//    @PostUpdate
//    private void createEventUpdate() {
//        createDomainEvents();
//        domainEvents.add(new ModelEvent<>(this, "update"));
//    }
//
//    @PostRemove
//    private void createEventRemove() {
//        createDomainEvents();
//        domainEvents.add(new ModelEvent<>(this, "remove"));
//    }
//
//    private void createDomainEvents() {
//        if (domainEvents == null) {
//            domainEvents = new ArrayList<>();
//        }
//    }
}
