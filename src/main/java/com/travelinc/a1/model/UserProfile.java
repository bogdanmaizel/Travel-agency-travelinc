package com.travelinc.a1.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.List;

@Entity

@Table(name = "user")

@NoArgsConstructor
@Getter
@Setter
@ToString
public class UserProfile {
    @ToString.Exclude
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "user-profile-gen")
    private Long id;

    @Column
    private String username;

    @Column
    private String password;

    @ToString.Exclude
    @ManyToMany(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
    @JoinTable(name = "user_booked_packages",
            joinColumns = @JoinColumn(name = "upid"),
            inverseJoinColumns = @JoinColumn(name = "vpid"))
    private List<VacationPackage> packages;

    public UserProfile(String username, String password) {
        this.username = username;
        this.password = password;
    }
}
