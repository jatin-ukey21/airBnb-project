package com.projects.airBnbApp.entities;

import com.projects.airBnbApp.enums.Gender;
import lombok.Data;

import javax.persistence.*;
import java.util.Set;

@Data
@Entity
public class Guest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Column(nullable = false)
    private String name;

    @Enumerated(EnumType.STRING)
    private Gender gender;


    private Integer age;

    //It is going to use the previous table created by Booking
    @ManyToMany(mappedBy = "guests")
    private Set<Booking> bookings;
}
