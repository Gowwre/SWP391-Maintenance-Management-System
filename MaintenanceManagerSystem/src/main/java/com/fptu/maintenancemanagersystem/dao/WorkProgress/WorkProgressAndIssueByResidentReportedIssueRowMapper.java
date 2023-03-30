package com.fptu.maintenancemanagersystem.dao.WorkProgress;

import com.fptu.maintenancemanagersystem.model.dto.WorkProgressAndIssueByResidentReportedIssue;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

public class WorkProgressAndIssueByResidentReportedIssueRowMapper implements RowMapper<WorkProgressAndIssueByResidentReportedIssue> {

    @Override
    public WorkProgressAndIssueByResidentReportedIssue mapRow(ResultSet rs, int rowNum) throws SQLException {
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
        Boolean isResidentCompletionConfirmation = rs.getBoolean("resident_completion_confirmation");
        String residentCompletionConfirmation = isResidentCompletionConfirmation ? "Chấp nhận" : "Không chấp nhận";
        int assignStaffId = rs.getInt("assign_staff_id");
        return new WorkProgressAndIssueByResidentReportedIssue(
                workProgressId, deadlineDate, createdDate, workStatus, completedDate,
                residentReportedIssueId, roomId, residentName, residentEmail, residentPhoneNumber, description,
                dateReported, residentCompletionConfirmation, assignStaffId);
    }
}
