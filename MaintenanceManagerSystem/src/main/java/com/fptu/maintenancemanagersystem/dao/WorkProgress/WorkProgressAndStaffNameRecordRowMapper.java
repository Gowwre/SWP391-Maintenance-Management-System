package com.fptu.maintenancemanagersystem.dao.WorkProgress;

import com.fptu.maintenancemanagersystem.model.dto.WorkProgressAndStaffNameRecord;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

public class WorkProgressAndStaffNameRecordRowMapper implements RowMapper<WorkProgressAndStaffNameRecord> {

    @Override
    public WorkProgressAndStaffNameRecord mapRow(ResultSet rs, int rowNum) throws SQLException {
        int workProgressId = rs.getInt("work_progress_id");
        LocalDate deadlineDate = null;
        if(rs.getDate("deadline_date") != null) {
            deadlineDate = rs.getDate("deadline_date").toLocalDate();
        }
        LocalDate createdDate = null;
        if(rs.getDate("created_date") != null) {
            createdDate = rs.getDate("created_date").toLocalDate();
        }
        String workStatus = rs.getString("work_status");
        LocalDate completedDate = null;
        if (rs.getDate("completed_date") != null) {
            completedDate = rs.getDate("completed_date").toLocalDate();
        }
        int issueId = rs.getInt("issue_id");
        String fullName = rs.getString("fullname");
        return new WorkProgressAndStaffNameRecord(workProgressId, deadlineDate, createdDate, workStatus, completedDate, issueId, fullName);
    }
}
