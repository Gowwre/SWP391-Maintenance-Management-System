package com.fptu.maintenancemanagersystem.dao.Manager;

import com.fptu.maintenancemanagersystem.model.Manager;
import org.springframework.jdbc.core.RowMapper;

public class ManagerMapper implements RowMapper<Manager> {
    @Override
    public Manager mapRow(java.sql.ResultSet rs, int rowNum) throws java.sql.SQLException {
        Manager manager = new Manager();
        manager.setManagerId(rs.getInt("manager_id"));
        manager.setName(rs.getString("name"));
        manager.setEmail(rs.getString("email"));
        manager.setPassword(rs.getString("password"));
        return manager;
    }
}

