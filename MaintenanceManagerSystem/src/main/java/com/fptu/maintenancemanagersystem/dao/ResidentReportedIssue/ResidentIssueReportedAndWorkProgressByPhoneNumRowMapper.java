package com.fptu.maintenancemanagersystem.dao.ResidentReportedIssue;


import com.fptu.maintenancemanagersystem.model.ResidentIssueReportedAndWorkProgressByPhoneNum;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

public class ResidentIssueReportedAndWorkProgressByPhoneNumRowMapper implements RowMapper<ResidentIssueReportedAndWorkProgressByPhoneNum> {
    @Override
    public ResidentIssueReportedAndWorkProgressByPhoneNum mapRow(ResultSet rs, int rowNum) throws SQLException {
        int workProgressId = rs.getInt("work_progress_id");
        LocalDate deadlineDate = rs.getDate("deadline_date").toLocalDate();
        LocalDate createdDate = rs.getDate("created_date").toLocalDate();
        String workStatus = rs.getString("work_status");
        LocalDate completedDate = null;
        if (rs.getDate("completed_date") != null) {
            completedDate = rs.getDate("completed_date").toLocalDate();
        }
        int residentReportedIssueId = rs.getInt("issue_id");
        int roomId = rs.getInt("room_id");
        String residentName = rs.getString("resident_name");
        String residentEmail = rs.getString("resident_email");
        String residentPhoneNumber = rs.getString("resident_phone_number");
        String description = rs.getString("description");
        LocalDate dateReported = rs.getDate("date_reported").toLocalDate();
        boolean isResidentCompletionConfirmation = rs.getBoolean("resident_completion_confirmation");
        String staffFullName = rs.getString("fullname");
        return new ResidentIssueReportedAndWorkProgressByPhoneNum(
                workProgressId, deadlineDate, createdDate, workStatus, completedDate,
                residentReportedIssueId, roomId, residentName, residentEmail, residentPhoneNumber, description,
                dateReported, isResidentCompletionConfirmation, staffFullName);
    }
}
