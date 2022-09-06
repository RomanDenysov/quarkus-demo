package com.demo.model.entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "cards")
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id")
@AllArgsConstructor
@NoArgsConstructor
public class Card extends PanacheEntityBase {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    @Column(name = "balance")
    public Float balance;

    @Column(name = "currency")
    @Enumerated(EnumType.STRING)
    public Currency currency;

    @ManyToMany(mappedBy = "cards")
    public List<User> users = new ArrayList<>();

    @OneToMany(mappedBy = "card")
    public List<Bill> bills = new ArrayList<>();
}
