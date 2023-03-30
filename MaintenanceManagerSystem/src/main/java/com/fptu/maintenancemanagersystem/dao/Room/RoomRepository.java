package com.fptu.maintenancemanagersystem.dao.Room;

import com.fptu.maintenancemanagersystem.model.entities.Room;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class RoomRepository {

    @Autowired
    JdbcTemplate jdbcTemplate;

    public List<Room> getAllRooms() {
        String SQL = "SELECT * FROM [Room]";
            return jdbcTemplate.query(SQL, new BeanPropertyRowMapper<>(Room.class));
    }
}
