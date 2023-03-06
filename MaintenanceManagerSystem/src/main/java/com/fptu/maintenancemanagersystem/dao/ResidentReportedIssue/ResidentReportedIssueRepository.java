package com.fptu.maintenancemanagersystem.dao.ResidentReportedIssue;


import com.fptu.maintenancemanagersystem.model.ResidentReportedIssue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

@Repository
public class ResidentReportedIssueRepository {
    @Autowired
    JdbcTemplate jdbcTemplate;

    public List<ResidentReportedIssue> getAll() throws Exception{
        String SQL = "SELECT * FROM [ResidentReportedIssue]";
            return jdbcTemplate.query(SQL, new BeanPropertyRowMapper<>(ResidentReportedIssue.class));
    }
    
    @Autowired
    NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Transactional
    public void insertReportForm(ResidentReportedIssue residentReportedIssue, List<Integer> equipmentIds) {
        insertResidentReportedIssue(residentReportedIssue);
        insertFaultedDevice(equipmentIds);
    }

    private void insertResidentReportedIssue(ResidentReportedIssue residentReportedIssue) {
        String SQL = "INSERT INTO [ResidentReportedIssue] (room_id, resident_name, resident_email, resident_phone_number, description, date_reported) VALUES (:roomId, :residentName, :residentEmail, :residentPhoneNumber, :description, :dateReported)";

        MapSqlParameterSource parameters = new MapSqlParameterSource()
                .addValue("roomId", residentReportedIssue.getRoomId())
                .addValue("residentName", residentReportedIssue.getResidentName())
                .addValue("residentEmail", residentReportedIssue.getResidentEmail())
                .addValue("residentPhoneNumber", residentReportedIssue.getResidentPhoneNumber())
                .addValue("description", residentReportedIssue.getDescription())
                .addValue("dateReported", Date.valueOf(LocalDate.now()));

        namedParameterJdbcTemplate.update(SQL, parameters);
    }

    private void insertFaultedDevice(List<Integer> equipmentIds) {
        int currentIssueId = getCurrentIssueId();

        for (int equipmentId : equipmentIds) {
            String SQL = "INSERT INTO [FaultedDevice] (issue_id, equipment_id) VALUES (:issueId, :equipmentId)";

            MapSqlParameterSource parameters = new MapSqlParameterSource()
                    .addValue("issueId", currentIssueId)
                    .addValue("equipmentId", equipmentId);

            namedParameterJdbcTemplate.update(SQL, parameters);
        }
    }

    private Integer getCurrentIssueId() {
        return jdbcTemplate.queryForObject("SELECT IDENT_CURRENT('ResidentReportedIssue')", Integer.class);
    }

}
