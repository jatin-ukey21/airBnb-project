package com.projects.airBnbApp.dto;

import com.projects.airBnbApp.entities.HotelContactInfo;
import com.projects.airBnbApp.entities.Room;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import java.time.LocalDateTime;
import java.util.List;

@Data
public class HotelDTO {
    private Long id;
    private String name;
    private String city;
    private String[] photos;
    private  String[] amenities;
    private HotelContactInfo contactInfo;
    private Boolean active;

}
