package com.travelinc.a1.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Date;
import java.util.List;

@Entity
@Table(name = "package")

@Getter
@Setter
@NoArgsConstructor
public class VacationPackage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "package-gen")
    private Long id;

    @Column(unique = true, nullable = false)
    private String name;

    @ManyToOne
    @JoinColumn(name = "did")
    private Destination destination;

    @Column
    private Date start;
    @Column
    private Date end;
    @Column
    private Integer price;
    @Column
    private Integer stock;
    @Column
    private String description;
    @Column
    private Integer bookedPlaces;
    @ManyToMany(mappedBy = "packages")
    private List<UserProfile> userProfiles;

    public VacationPackage(String name, Destination destination, Date start, Date end, Integer price, Integer stock, String description) {
        this.name = name;
        this.destination = destination;
        this.start = start;
        this.end = end;
        this.price = price;
        this.stock = stock;
        this.description = description;
    }
}
