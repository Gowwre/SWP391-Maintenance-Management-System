/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fptu.maintenancemanagersystem.dao.Floor;

import com.fptu.maintenancemanagersystem.model.Floor;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

/**
 *
 * @author lmphi
 */
@Repository
public class FloorRepository {
    @Autowired
    JdbcTemplate jdbcTemplate;

    public List<Floor> getAll() {
        String SQL = "SELECT * FROM [Floor]";
        return jdbcTemplate.query(SQL, new BeanPropertyRowMapper<>(Floor.class));
    }
    
    public Floor get() {
        return null;
    }
    
    public void update() {
        
    }
    
}
