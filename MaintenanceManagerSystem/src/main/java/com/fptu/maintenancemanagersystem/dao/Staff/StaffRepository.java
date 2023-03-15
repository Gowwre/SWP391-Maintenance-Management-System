package com.fptu.maintenancemanagersystem.dao.Staff;

import com.fptu.maintenancemanagersystem.model.Staff;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class StaffRepository {
    @Autowired
    JdbcTemplate jdbcTemplate;

    @Autowired
    NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public List<Staff> getAllStaff() {
        String SQL = "SELECT * FROM [Staff]";
        return jdbcTemplate.query(SQL, new RowMapper<Staff>() {
            @Override
            public Staff mapRow(ResultSet rs, int rowNum) throws SQLException {
                Staff staff = new Staff();
                staff.setStaffId(rs.getInt("staff_id"));
                staff.setFullname(rs.getString("fullname"));
                staff.setManagerId(rs.getInt("manager_id"));
                staff.setDateOfBirth(rs.getDate("date_of_birth").toLocalDate());
                staff.setEmail(rs.getString("email"));
                staff.setPhoneNumber(rs.getString("phone_number"));
                staff.setPassword(rs.getString("password"));
                staff.setBeginWorkDate(rs.getDate("begin_work_date").toLocalDate());
                staff.setWorking(rs.getBoolean("is_working"));
                staff.setFloorId(rs.getInt("floor_id"));
                return staff;
            }
        });
    }

    public List<Staff> getWorkingStaff() {
        String SQL = "SELECT * FROM [Staff] WHERE is_working = 1";
        return jdbcTemplate.query(SQL, new RowMapper<Staff>() {
            @Override
            public Staff mapRow(ResultSet rs, int rowNum) throws SQLException {
                Staff staff = new Staff();
                staff.setStaffId(rs.getInt("staff_id"));
                staff.setFullname(rs.getString("fullname"));
                staff.setManagerId(rs.getInt("manager_id"));
                staff.setDateOfBirth(rs.getDate("date_of_birth").toLocalDate());
                staff.setEmail(rs.getString("email"));
                staff.setPhoneNumber(rs.getString("phone_number"));
                staff.setPassword(rs.getString("password"));
                staff.setBeginWorkDate(rs.getDate("begin_work_date").toLocalDate());
                staff.setWorking(rs.getBoolean("is_working"));
                staff.setFloorId(rs.getInt("floor_id"));
                return staff;
            }
        });
    }


    public Staff findUserByLogin(String email, String password) {
        String SQL = "Select * From [Staff] Where email=? and password=?";
        try {
            return jdbcTemplate.queryForObject(SQL, new Object[]{email, password}, new BeanPropertyRowMapper<>(Staff.class));
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

    public Staff findStaffById(int staffId) {
        String SQL = "Select * From [Staff] Where staff_id=?";
        try {
            return jdbcTemplate.queryForObject(SQL, new Object[]{staffId}, new RowMapper<Staff>() {
                @Override
                public Staff mapRow(ResultSet rs, int rowNum) throws SQLException {
                    Staff staff = new Staff();
                    staff.setStaffId(rs.getInt("staff_id"));
                    staff.setFullname(rs.getString("fullname"));
                    staff.setManagerId(rs.getInt("manager_id"));
                    staff.setDateOfBirth(rs.getDate("date_of_birth").toLocalDate());
                    staff.setEmail(rs.getString("email"));
                    staff.setPhoneNumber(rs.getString("phone_number"));
                    staff.setPassword(rs.getString("password"));
                    staff.setBeginWorkDate(rs.getDate("begin_work_date").toLocalDate());
                    staff.setWorking(rs.getBoolean("is_working"));
                    staff.setFloorId(rs.getInt("floor_id"));
                    return staff;
                }
            });
        } catch (DataAccessException e) {
            return null;
        }
    }

    public void markWorkProgressAsComplete(int issueId) {

        Integer workProgressId = getWorkProgressIdByIssueId(issueId);

        String sql = """
                Update [WorkProgress] set work_status=:workStatus, completed_date=:completeDate where work_progress_id=:workProgressId
                """;
        MapSqlParameterSource parameters = new MapSqlParameterSource()
                .addValue("workStatus", "Completed")
                .addValue("completeDate", java.sql.Date.valueOf(java.time.LocalDate.now()))
                .addValue("workProgressId", workProgressId);

        namedParameterJdbcTemplate.update(sql, parameters);

    }

    private Integer getWorkProgressIdByIssueId(int issueId) {
        return jdbcTemplate.queryForObject("SELECT DISTINCT work_progress_id FROM [FaultedDevice] WHERE issue_id=?", new Object[]{issueId}, Integer.class);
    }
}
