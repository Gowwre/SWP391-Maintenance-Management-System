package com.fptu.maintenancemanagersystem.dao.WorkProgress;

import com.fptu.maintenancemanagersystem.model.WorkProgress;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class WorkProgressRepository {
    @Autowired
    JdbcTemplate jdbcTemplate;

    public WorkProgress findById(int id) {
        String SQL = "SELECT * FROM WorkProgress WHERE workProgressId = ?";
        return jdbcTemplate.queryForObject(SQL, new Object[]{id}, new BeanPropertyRowMapper<>(WorkProgress.class));
    }
}
