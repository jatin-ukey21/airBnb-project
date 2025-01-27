package com.projects.airBnbApp.services;

import com.projects.airBnbApp.dto.HotelDTO;
import com.projects.airBnbApp.entities.Hotel;

public interface HotelService {
    HotelDTO createNewHotel(HotelDTO hotelDTO);

    HotelDTO getHotelById(Long id);
}
