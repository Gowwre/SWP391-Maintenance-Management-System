/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fptu.maintenancemanagersystem.dao.Equipment;

import com.fptu.maintenancemanagersystem.model.Equipment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 *
 * @author lmphi
 */
@Repository
public class EquipmentRepository {
   @Autowired
    JdbcTemplate
    jdbcTemplate;

   public List<Equipment> getAllEquipmentsByRoom(int roomId) {
       String SQL = "select * from \n" +
               "Equipment e join Room r \n" +
               "on e.room_id =r.room_id \n" +
               "WHERE e.room_id =?";
       return jdbcTemplate.query(SQL, new Object[]{roomId}, new EquipmentMapper());
   }
}
