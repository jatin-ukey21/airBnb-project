package com.projects.airBnbApp.controllers;

import com.projects.airBnbApp.dto.HotelDTO;
import com.projects.airBnbApp.services.HotelService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin/hotels")
@RequiredArgsConstructor
@Slf4j
public class HotelController {
    private final HotelService hotelService;

    @PostMapping
    public ResponseEntity<HotelDTO> createNewHotel(@RequestBody HotelDTO hotelDTO){
        log.info("Attempting to create a new hotel with name: " + hotelDTO.getName());
        HotelDTO hotel = hotelService.createNewHotel(hotelDTO);
        return new ResponseEntity<>(hotel, HttpStatus.CREATED);
    }

    @GetMapping("/{hotelId}")
    public  ResponseEntity<HotelDTO> getHotelById(@PathVariable Long hotelId){
        HotelDTO hotelDTO = hotelService.getHotelById(hotelId);
        return ResponseEntity.ok(hotelDTO);
    }

    @PutMapping("/{hotelId}")
    public  ResponseEntity<HotelDTO> updateHotelById(@PathVariable Long hotelId,@RequestBody HotelDTO hotelDTO){
        HotelDTO hotel = hotelService.updateHotelById(hotelId,hotelDTO);
        return ResponseEntity.ok(hotel);
    }

    @DeleteMapping("/{hotelId}")
    public  ResponseEntity<Void> deleteHotelById(@PathVariable Long hotelId){
      hotelService.deleteHotelById(hotelId);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{hotelId}")
    public  ResponseEntity<Void> activateHotel(@PathVariable Long hotelId){
        hotelService.activateHotel(hotelId);
        return ResponseEntity.noContent().build();
    }
}
