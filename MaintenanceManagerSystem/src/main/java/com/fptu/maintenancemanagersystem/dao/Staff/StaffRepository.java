package com.fptu.maintenancemanagersystem.dao.Staff;

import com.fptu.maintenancemanagersystem.model.Staff;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class StaffRepository {
    @Autowired
    JdbcTemplate jdbcTemplate;

    @Autowired
    NamedParameterJdbcTemplate namedParameterJdbcTemplate;


    public Staff findUserByLogin(String email, String password) {
        String SQL = "Select * From [Staff] Where email=? and password=?";
        try {
            Staff staff = jdbcTemplate.queryForObject(SQL, new Object[]{email, password}, new BeanPropertyRowMapper<>(Staff.class));
            return staff;
        } catch (DataAccessException e) {
            return null;
        }

    }

    public void setNewPassword(String email ,String currentPassword, String newPassword) {
        String SQL = "Update [Staff] set password=:newPassword where email=:email and password=:currentPassword";

        MapSqlParameterSource parameters = new MapSqlParameterSource();
        parameters.addValue("email", email);
        parameters.addValue("currentPassword", currentPassword);
        parameters.addValue("newPassword", newPassword);

        namedParameterJdbcTemplate.update(SQL, parameters);

    }


}
