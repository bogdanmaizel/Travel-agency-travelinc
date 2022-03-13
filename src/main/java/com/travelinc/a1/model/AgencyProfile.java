package com.travelinc.a1.model;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "agency")
@Getter
@NoArgsConstructor
public class AgencyProfile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "agency-gen")
    private Long id;

    @OneToOne
    @JoinColumn(name = "uid")
    private User user;

    public AgencyProfile(User user) {
        this.user = user;
    }
}
