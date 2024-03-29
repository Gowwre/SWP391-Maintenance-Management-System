/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fptu.maintenancemanagersystem.dao.Equipment;

import com.fptu.maintenancemanagersystem.model.entities.Equipment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author lmphi
 */
@Repository
public class EquipmentRepository {
    @Autowired
    JdbcTemplate
            jdbcTemplate;

    public List<Equipment> getAll() throws Exception {
        String SQL = "Select * from [Equipment]";
        return jdbcTemplate.query(SQL, new BeanPropertyRowMapper<>(Equipment.class));
    }

    public List<Equipment> getAllEquipmentsByRoom(int roomId) {
        String SQL = """
                select * from Equipment e join Room r on e.room_id =r.room_id
                WHERE r.room_id =?
                """;
        return jdbcTemplate.query(SQL, new Object[]{roomId}, new BeanPropertyRowMapper<>(Equipment.class));
    }

    public List<Equipment> getAllEquipments() {
        String SQL = "Select * from Equipment";
        return jdbcTemplate.query(SQL, new BeanPropertyRowMapper<>(Equipment.class));
    }

    public List<Equipment> getEquipmentsByIssueId(int issueId) {
        String SQL = """
                select e.equipment_id,e.equipment_name,e.brand from
                 FaultedDevice fd inner join Equipment e on fd.equipment_id = e.equipment_id where fd.issue_id=?
                """;
        return jdbcTemplate.query(SQL, new Object[]{issueId}, new BeanPropertyRowMapper<>(Equipment.class));

    }
}
