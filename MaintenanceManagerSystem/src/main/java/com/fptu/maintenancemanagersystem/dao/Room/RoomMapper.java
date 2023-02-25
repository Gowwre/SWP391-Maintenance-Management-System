package com.fptu.maintenancemanagersystem.dao.Room;

import com.fptu.maintenancemanagersystem.model.Room;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class RoomMapper implements RowMapper<Room> {
    @Override
    public Room mapRow(ResultSet rs, int rowNum) throws SQLException {
        Room room = new Room();
        room.setRoomId(rs.getInt("room_id"));
        room.setRoomNumber(rs.getString("room_number"));
        room.setFloorId(rs.getInt("floor_id"));
        room.setOccupied(rs.getBoolean("is_occupied"));
        room.setStaffId(rs.getInt("staff_id"));
        return room;
    }
}
