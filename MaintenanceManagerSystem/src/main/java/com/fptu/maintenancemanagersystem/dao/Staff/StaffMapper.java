package com.fptu.maintenancemanagersystem.dao.Staff;

import com.fptu.maintenancemanagersystem.model.Staff;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class StaffMapper implements RowMapper<Staff> {
    @Override
    public Staff mapRow(ResultSet rs, int rowNum) throws SQLException {
        Staff staff = new Staff();

        staff.setStaffId(rs.getInt("staff_id"));
        staff.setFullname(rs.getString("fullname"));
        staff.setManagerId(rs.getInt("manager_id"));
        staff.setDateOfBirth(rs.getDate("date_of_birth").toLocalDate());
        staff.setSpecialization(rs.getString("specialization"));
        staff.setEmail(rs.getString("email"));
        staff.setPhoneNumber(rs.getString("phone_number"));
        staff.setPassword(rs.getString("password"));
        staff.setBeginWorkDate(rs.getDate("begin_work_date").toLocalDate());
        staff.setWorking(rs.getBoolean("is_working"));

        return staff;
    }
}
