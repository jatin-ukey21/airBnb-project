package com.projects.airBnbApp.services;

import com.projects.airBnbApp.entities.Room;

public interface InventoryService {
    void initializeRoomForAYear(Room room);

    void deleteFutureInventories(Room room);
}
