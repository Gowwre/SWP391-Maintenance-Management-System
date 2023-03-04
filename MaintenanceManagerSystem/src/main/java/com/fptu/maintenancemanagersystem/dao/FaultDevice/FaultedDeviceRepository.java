/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fptu.maintenancemanagersystem.dao.FaultDevice;

import com.fptu.maintenancemanagersystem.model.FaultedDevice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 *
 * @author lmphi
 */
@Repository
public class FaultedDeviceRepository {
    @Autowired
    JdbcTemplate jdbcTemplate;
    public List<FaultedDevice> getAll() {
        String SQL = "Select * from FaultedDevice";
        return jdbcTemplate.query(SQL, new BeanPropertyRowMapper<>(FaultedDevice.class));
    }
    
    public FaultedDevice get() {
        return null;
    }
    
    public void updateAssignedStaff(int faultDeviceId, int assignedStaffId) {
        String sql = "UPDATE FaultedDevice SET assign_staff_id = ? WHERE faulted_device_id = ?";
        try {
            jdbcTemplate.update(sql, assignedStaffId, faultDeviceId);
        }catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }



    public void update() {
        
    }
    
    public void delete() {
        
    }

}
