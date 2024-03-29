/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fptu.maintenancemanagersystem.dao.FaultDevice;

import com.fptu.maintenancemanagersystem.model.entities.FaultedDevice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author lmphi
 */
@Repository
public class FaultedDeviceRepository {
    @Autowired
    JdbcTemplate jdbcTemplate;

    @Autowired
    NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public List<FaultedDevice> getAll() {
        String SQL = "Select * from FaultedDevice";
        return jdbcTemplate.query(SQL, new BeanPropertyRowMapper<>(FaultedDevice.class));
    }

    public FaultedDevice get() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void updateAssignedStaff(int assignStaffId, int issueId) {
        String sql = "UPDATE FaultedDevice SET assign_staff_id = :assignStaffId WHERE issue_id = :issueId";

        MapSqlParameterSource parameters = new MapSqlParameterSource();
        parameters.addValue("assignStaffId", assignStaffId);
        parameters.addValue("issueId", issueId);

        namedParameterJdbcTemplate.update(sql, parameters);
    }


}
