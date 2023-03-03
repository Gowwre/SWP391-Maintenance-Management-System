/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fptu.maintenancemanagersystem.dao.FaultDevice;

import com.fptu.maintenancemanagersystem.model.FaultDevice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 *
 * @author lmphi
 */
@Repository
public class FaultDeviceRepository {
    @Autowired
    JdbcTemplate jdbcTemplate;
    public List<FaultDevice> getAll() {
        return null;
    }
    
    public FaultDevice get() {
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
