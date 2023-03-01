package com.fptu.maintenancemanagersystem.dao.Equipment;

import com.fptu.maintenancemanagersystem.model.Equipment;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class EquipmentMapper implements RowMapper<Equipment> {
    @Override
    public Equipment mapRow(ResultSet rs, int rowNum) throws SQLException {
        Equipment equipment = new Equipment();
        equipment.setEquipmentId(rs.getInt("equipment_id"));
        equipment.setEquipmentName(rs.getString("equipment_name"));
        equipment.setRoomId(rs.getInt("room_id"));
        equipment.setBrand(rs.getString("brand"));
        equipment.setDescription(rs.getString("description"));
        return equipment;
    }
}
