package com.projects.airBnbApp.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(

        uniqueConstraints = @UniqueConstraint
                (
                        name = "unique_hotel_room_date",
                        columnNames = {"hotel_id","room_id","date"}
                ))
public class Inventory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    /*
     fetch = FetchType.EAGER:

The fetch attribute defines how related entities are loaded from the database.
FetchType.EAGER means that whenever an instance of the current entity (e.g., Inventory) is loaded, the associated Hotel entity will also be loaded immediately, without delay.
This is opposed to FetchType.LAZY, which would only load the Hotel entity when explicitly accessed (i.e., when the hotel field is accessed in the code).
    * */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "hotel_id",nullable = false) //one hotel can have many inventories
    private Hotel hotel;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "room_id",nullable = false)
    private Room room;

    @Column(nullable = false)
    private LocalDate date;

    @Column(nullable = false,columnDefinition = "INTEGER DEFAULT 0")
    private Integer bookedCount;

    @Column(nullable = false)
    private Integer totalCount;

    @Column(nullable = false,precision = 5,scale = 2)
    private BigDecimal surgeFactor;

    @Column(nullable = false,precision = 10,scale = 2)
    private BigDecimal price;  //baseprice *  surgeFactor

    @Column(nullable = false)
    private String city;

    @Column(nullable = false)
    private boolean closed; //particular hotel room is not available at a particular date

    @CreationTimestamp
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;
}
