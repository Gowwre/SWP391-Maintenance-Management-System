package com.fptu.maintenancemanagersystem.dao.ResidentReportedIssue;

import com.fptu.maintenancemanagersystem.model.ResidentReportedIssue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

@Repository
public class ResidentReportedIssueRepository {
    @Autowired
    JdbcTemplate jdbcTemplate;

    public List<ResidentReportedIssue> getAll() throws Exception{
        String SQL = "SELECT * FROM [ResidentReportedIssue]";
            return jdbcTemplate.query(SQL, new ResidentReportedIssueMapper());
    }
    
    public void insertReportForm(ResidentReportedIssue residentReportedIssue, List<Integer> equipmentIds) {
        insertResidentReportedIssue(residentReportedIssue);
        insertFaultedDevice(equipmentIds);
    }

    private void insertResidentReportedIssue(ResidentReportedIssue residentReportedIssue) {
        String SQL = "INSERT INTO [ResidentReportedIssue] (room_id,resident_name,resident_email,resident_phone_number,description,date_reported) VALUES (?,?,?,?,?,?)";
        jdbcTemplate.update(SQL, residentReportedIssue.getRoomId(),
                residentReportedIssue.getResidentName(),
                residentReportedIssue.getResidentEmail(),
                residentReportedIssue.getResidentPhoneNumber(),
                residentReportedIssue.getDescription(),
                Date.valueOf(LocalDate.now()));
    }

    private void insertFaultedDevice(List<Integer> equipmentIds) {
        int currentIssueId = getCurrentIssueId();

        for (int equipmentId : equipmentIds) {
            String SQL2 = "INSERT INTO [FaultedDevice] (issue_id,equipment_id) VALUES (?,?)";
            jdbcTemplate.update(SQL2, currentIssueId, equipmentId);
        }
    }

    private Integer getCurrentIssueId() {
        return jdbcTemplate.queryForObject("SELECT IDENT_CURRENT('ResidentReportedIssue')", Integer.class);
    }

}
