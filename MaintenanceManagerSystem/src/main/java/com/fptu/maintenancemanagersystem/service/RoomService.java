package com.fptu.maintenancemanagersystem.service;

import com.fptu.maintenancemanagersystem.dao.Room.RoomRepository;
import com.fptu.maintenancemanagersystem.model.Room;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoomService {
    @Autowired
    RoomRepository roomRepository;

    public List<Room> getAllRooms()  {
        return roomRepository.getAllRooms();
    }
}
