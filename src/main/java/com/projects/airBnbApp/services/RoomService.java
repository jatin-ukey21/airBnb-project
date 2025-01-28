package com.projects.airBnbApp.services;


import com.projects.airBnbApp.dto.RoomDTO;

import java.util.List;

public interface RoomService {
    RoomDTO createNewRoom(Long hotelId,RoomDTO roomDTO);

    List<RoomDTO> getAllRoomsInHotel(Long id);

    RoomDTO getRoomById(Long roomId);

    void deleteRoomById(Long roomId);
}
