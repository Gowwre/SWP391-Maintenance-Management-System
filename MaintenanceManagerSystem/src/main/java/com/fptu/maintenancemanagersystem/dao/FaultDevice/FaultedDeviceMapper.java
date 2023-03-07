/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fptu.maintenancemanagersystem.dao.FaultDevice;

import com.fptu.maintenancemanagersystem.model.FaultedDevice;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;

/**
 *
 * @author lmphi
 */
public class FaultedDeviceMapper implements RowMapper<FaultedDevice>{
    @Override
    public FaultedDevice mapRow(ResultSet rs, int rowNum) throws SQLException {
        FaultedDevice faultedDevice = new FaultedDevice();
        faultedDevice.setFaultedDeviceId(rs.getInt("faulted_device_id"));
        faultedDevice.setEquipmentId(rs.getInt("equipment_id"));
        faultedDevice.setIssueId(rs.getInt("issue_id"));
        faultedDevice.setAssignStaffId(rs.getInt("assign_staff_id"));
        
        return faultedDevice;
    }
}
