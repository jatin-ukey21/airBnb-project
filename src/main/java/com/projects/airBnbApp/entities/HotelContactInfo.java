package com.projects.airBnbApp.entities;

import lombok.Data;

import javax.persistence.Embeddable;

@Data
@Embeddable
public class HotelContactInfo {
    private String address;
    private String phoneNumber;
    private String email;
    private String location;
}
