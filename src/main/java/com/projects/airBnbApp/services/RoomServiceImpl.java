package com.projects.airBnbApp.services;


import com.projects.airBnbApp.dto.RoomDTO;
import com.projects.airBnbApp.entities.Hotel;
import com.projects.airBnbApp.entities.Room;
import com.projects.airBnbApp.exceptions.ResourceNotFoundException;
import com.projects.airBnbApp.repository.HotelRepository;
import com.projects.airBnbApp.repository.RoomRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class RoomServiceImpl implements RoomService{
    private final RoomRepository roomRepository;
    private final ModelMapper modelMapper;
    private final InventoryService inventoryService;
    private final HotelRepository hotelRepository;
    @Override
    public RoomDTO createNewRoom(Long hotelId, RoomDTO roomDTO) {
        log.info("Creating a new Room in hotel with id:"+hotelId);
        Hotel hotel = hotelRepository
                .findById(hotelId)
                .orElseThrow(() -> new ResourceNotFoundException("Hotel not found with id:"+hotelId));
        Room room = modelMapper.map(roomDTO,Room.class);
        room.setHotel(hotel);
        room = roomRepository.save(room);

        // TODO: create inventory as soon as room is created and hotel is active
        if (hotel.getActive()){
            inventoryService.initializeRoomForAYear(room);
        }
        return modelMapper.map(room,RoomDTO.class);
    }

    @Override
    public List<RoomDTO> getAllRoomsInHotel(Long hotelId) {

        log.info("Getting all Rooms in hotel with id:"+hotelId);
        Hotel hotel = hotelRepository
                .findById(hotelId)
                .orElseThrow(() -> new ResourceNotFoundException("Hotel not found with id:"+hotelId));

        return hotel.getRooms()
                .stream().
                map((element) -> modelMapper.map(element, RoomDTO.class)).collect(Collectors.toList());

    }

    @Override
    public RoomDTO getRoomById(Long roomId) {
        log.info("Getting a  Room  with id:"+roomId);
        Room room = roomRepository
                .findById(roomId)
                .orElseThrow(() -> new ResourceNotFoundException("Room not found with id:"+roomId));

        return modelMapper.map(room,RoomDTO.class);
    }

    @Transactional
    @Override
    public void deleteRoomById(Long roomId) {
        log.info("Deleting a  Room  with id:"+roomId);
//        boolean exists = roomRepository.existsById(roomId);
//        if (!exists){
//            throw new ResourceNotFoundException("Room not found with id:"+roomId);
//        }
        Room room = roomRepository
                .findById(roomId)
                .orElseThrow(() -> new ResourceNotFoundException("Room not found with id:"+roomId));
        // TODO: delete all future inventory for this room
        inventoryService.deleteFutureInventories(room);
        roomRepository.deleteById(roomId);

    }
}
