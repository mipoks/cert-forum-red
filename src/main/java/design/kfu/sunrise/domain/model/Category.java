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
public class Category extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String description;

    //ToDo дописать
    @ManyToOne
    private Category parent;

    @OneToMany
    private Set<Category> childs = new HashSet<>();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Category category = (Category) o;
        return id != null && Objects.equals(id, category.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }


//    @Transient
//    private List<ModelEvent<Category>> domainEvents = new ArrayList<>();
//
//    @DomainEvents
//    private List<ModelEvent<Category>> domainEvents() {
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
