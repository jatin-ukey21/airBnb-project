package com.projects.airBnbApp.services;

import com.projects.airBnbApp.dto.HotelDTO;
import com.projects.airBnbApp.entities.Hotel;
import com.projects.airBnbApp.exceptions.ResourceNotFoundException;
import com.projects.airBnbApp.repository.HotelRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class HotelServiceImpl implements HotelService{

    private final HotelRepository hotelRepository;
    private final ModelMapper modelMapper;
    @Override
    public HotelDTO createNewHotel(HotelDTO hotelDTO) {
        log.info("Creating a new hotel with name {}",hotelDTO.getName());
        Hotel hotel = modelMapper.map(hotelDTO, Hotel.class);
        hotel.setActive(false);
        hotel = hotelRepository.save(hotel);
        log.info("Created a new hotel with ID : {}",hotelDTO.getId());
        return modelMapper.map(hotel,HotelDTO.class);
    }

    @Override
    public HotelDTO getHotelById(Long id) {
        log.info("Getting the hotel with id {}",id);
        Hotel hotel = hotelRepository
                .findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Hotel not found with id:"+id));
        return modelMapper.map(hotel,HotelDTO.class);
    }

    @Override
    public HotelDTO updateHotelById(Long id, HotelDTO hotelDTO) {
        log.info("Updating the hotel with id {}",id);
        Hotel hotel = hotelRepository
                .findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Hotel not found with id:"+id));
        modelMapper.map(hotelDTO,hotel);
        hotel.setId(id); //to avoid causing exception
        hotel = hotelRepository.save(hotel);
        return  modelMapper.map(hotel,HotelDTO.class);
    }

    @Override
    public void deleteHotelById(Long id) {
        boolean exists = hotelRepository.existsById(id);
        if (!exists) throw new ResourceNotFoundException("Hotel not found with id:"+id);

        hotelRepository.deleteById(id);
        //TODO : delete the future inventories for this hotel

    }

    @Override
    public void activateHotel(Long id) {
            log.info("Activating the hotel with id {}",id);
        Hotel hotel = hotelRepository
                .findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Hotel not found with id:"+id));
        hotel.setActive(true);

        //TODO: Create inventory for all the rooms for this hotel

    }
}
