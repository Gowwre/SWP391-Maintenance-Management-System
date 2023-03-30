package com.fptu.maintenancemanagersystem.dao.ResidentReportedIssue;

import com.fptu.maintenancemanagersystem.model.entities.ResidentReportedIssue;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ResidentReportedIssueRowMapper implements RowMapper<ResidentReportedIssue> {
    @Override
    public ResidentReportedIssue mapRow(ResultSet rs, int rowNum) throws SQLException {
        ResidentReportedIssue residentReportedIssue = new ResidentReportedIssue();
        residentReportedIssue.setIssueId(rs.getInt("issue_id"));
        residentReportedIssue.setRoomId(rs.getInt("room_id"));
        residentReportedIssue.setResidentName(rs.getString("resident_name"));
        residentReportedIssue.setResidentEmail(rs.getString("resident_email"));
        residentReportedIssue.setResidentPhoneNumber(rs.getString("resident_phone_number"));
        residentReportedIssue.setDescription(rs.getString("description"));
        residentReportedIssue.setDateReported(rs.getDate("date_reported").toLocalDate());
        residentReportedIssue.setResidentCompletionConfirmation(rs.getBoolean("resident_completion_confirmation"));
        return residentReportedIssue;
    }
}
