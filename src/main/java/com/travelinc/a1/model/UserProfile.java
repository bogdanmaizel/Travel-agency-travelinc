package com.travelinc.a1.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.List;

@Entity

@Table(name = "user-profile")

@NoArgsConstructor
@Getter
@Setter
@ToString
public class UserProfile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "user-profile-gen")
    private Long id;

    @OneToOne
    @JoinColumn(name = "uid")
    @ToString.Exclude
    private User user;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "user_booked_packages",
            joinColumns = @JoinColumn(name = "upid"),
            inverseJoinColumns = @JoinColumn(name = "vpid"))
    private List<VacationPackage> packages;

    public UserProfile(User user) {
        this.user = user;
    }
}
