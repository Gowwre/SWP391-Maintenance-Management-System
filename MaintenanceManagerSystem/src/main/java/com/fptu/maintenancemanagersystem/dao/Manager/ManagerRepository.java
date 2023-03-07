package com.fptu.maintenancemanagersystem.dao.Manager;

import com.fptu.maintenancemanagersystem.model.Manager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class ManagerRepository {
    @Autowired
    private
    JdbcTemplate jdbcTemplate;

    @Autowired
    NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public Manager findUserByLogin(String email, String password) {
        String SQL = "Select * From [Manager] Where email=? and password=?";
        try {
            Manager manager = jdbcTemplate.queryForObject(SQL, new Object[]{email, password}, new BeanPropertyRowMapper<>(Manager.class));
            return manager;
        } catch (DataAccessException e) {
            return null;
        }
    }

    public void setNewPassword(String email, String currentPassword, String newPassword) {
        String SQL = "Update [Manager] Set password=:newPassword Where email=:email and password=:currentPassword";

        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("email", email);
        params.addValue("currentPassword", currentPassword);
        params.addValue("newPassword", newPassword);
        namedParameterJdbcTemplate.update(SQL, params);
    }
}
