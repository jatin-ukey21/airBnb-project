package com.projects.airBnbApp.repository;

import com.projects.airBnbApp.entities.Inventory;
import com.projects.airBnbApp.entities.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;

@Repository
public interface InventoryRepository extends JpaRepository<Inventory,Long> {
    void deleteByDateAfterAndRoom(LocalDate date, Room room);

    boolean existsByRoomAndDate(Room room, LocalDate date);
}
