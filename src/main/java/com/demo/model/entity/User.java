package com.demo.model.entity;

import com.demo.model.dto.UserDTO;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Entity
@Table(name = "users")
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id")
@AllArgsConstructor
@RequiredArgsConstructor
@NoArgsConstructor
public class User extends PanacheEntityBase {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    @Column(name = "name", unique = true)
    @NonNull
    public String name;

    @Column(name = "role")
    @Enumerated(EnumType.STRING)
    @NonNull
    public Role role;

    @ManyToMany
    @JoinTable(
            name = "users_cards",
            joinColumns = {@JoinColumn(name = "user_id")},
            inverseJoinColumns = {@JoinColumn(name = "card_id")}
    )
    public List<Card> cards = new ArrayList<>();

    @Column(name = "contact_id", unique = true)
    public Long contactId;

    public static Optional<User> findUserByName(String name) {
        return find("name", name).firstResultOptional();
    }

    public static void updateUserName(Long id, String name) {
        update("SET name=?1 WHERE id=?2", name, id);
    }

    public static void updateUserRole(Long id, Role role) {
        update("SET role=?1 WHERE id=?2", role, id);
    }

    public static List<UserDTO> findUserRestrictInfo() {
        EntityManager entityManager = getEntityManager();
        Query query = entityManager.createNativeQuery("SELECT u.name, u.role FROM users u");
        Stream<Object[]> stream = query.getResultStream();
        return stream.map(
                        objs -> UserDTO.builder()
                                .name((String) objs[0])
                                .role(Role.valueOf((String) objs[1]))
                                .build())
                .collect(Collectors.toList());
    }
}
