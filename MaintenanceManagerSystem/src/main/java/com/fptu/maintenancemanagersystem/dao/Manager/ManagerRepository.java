package com.fptu.maintenancemanagersystem.dao.Manager;

import com.fptu.maintenancemanagersystem.model.Manager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class ManagerRepository {
    @Autowired
    private
    JdbcTemplate jdbcTemplate;

    public Manager findUserByLogin(String email, String password) {
        String SQL = "Select * From [Manager] Where email=? and password=?";
        try {
            Manager manager = jdbcTemplate.queryForObject(SQL,new Object[]{email,password}, new BeanPropertyRowMapper<>(Manager.class));
            return manager;
        } catch (DataAccessException e) {
            return null;
        }
    }
}
