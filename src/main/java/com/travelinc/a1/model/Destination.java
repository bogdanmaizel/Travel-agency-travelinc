package com.travelinc.a1.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "destination")
@Getter
@NoArgsConstructor
public class Destination {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "destination-gen")
    private Long id;

    @Column(unique = true, nullable = false)
    private String name;

    @OneToMany(mappedBy = "destination", fetch = FetchType.EAGER, orphanRemoval = true)
    private List<VacationPackage> packsList;

    @Override
    public String toString() {
        return this.name;
    }

    public Destination(String name) {
        this.name = name;
    }
}
