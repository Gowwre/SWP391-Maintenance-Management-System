package com.fptu.maintenancemanagersystem.dao.Staff;

import com.fptu.maintenancemanagersystem.model.Staff;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class StaffRepository {
    @Autowired
    private
    JdbcTemplate jdbcTemplate;


    public Staff findUserByLogin(String email, String password) {
        String SQL = "Select * From [Staff] Where email=? and password=?";
        try {
            Staff staff = jdbcTemplate.queryForObject(SQL,new Object[]{email,password}, new BeanPropertyRowMapper<>(Staff.class));
            return staff;
        } catch (DataAccessException e) {
            return null;
        }

    }


}
