package com.fptu.maintenancemanagersystem.dao.ResidentReportedIssue;

import com.fptu.maintenancemanagersystem.model.ResidentReportedIssue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.time.LocalDate;

@Repository
public class ResidentReportedIssueRepository {
    @Autowired
    JdbcTemplate jdbcTemplate;

    public void insertResidentReportedIssue(ResidentReportedIssue residentReportedIssue) {
        String SQL = "INSERT INTO [ResidentReportedIssue] (room_id,resident_name,resident_email,resident_phone_number,description,date_reported) VALUES (?,?,?,?,?,?)";
        jdbcTemplate.update(SQL, residentReportedIssue.getRoomId(),
                residentReportedIssue.getResidentName(),
                residentReportedIssue.getResidentEmail(),
                residentReportedIssue.getResidentPhoneNumber(),
                residentReportedIssue.getDescription(),
                Date.valueOf(LocalDate.now()));
    }

}
