package com.fptu.maintenancemanagersystem.dao.WorkProgress;

import com.fptu.maintenancemanagersystem.model.WorkProgress;
import com.fptu.maintenancemanagersystem.model.WorkProgressAndIssueByResidentReportedIssue;
import com.fptu.maintenancemanagersystem.model.WorkProgressAndStaffNameRecord;
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
        String SQL = "SELECT * FROM WorkProgress WHERE work_progress_id = ?";
        return jdbcTemplate.queryForObject(SQL, new Object[]{id}, new BeanPropertyRowMapper<>(WorkProgress.class));
    }

    public List<WorkProgressAndIssueByResidentReportedIssue> findWorkProgressAndIssueByResidentReportedIssue() {
        String SQL = """
                     SELECT wp.*, rri.*, fd.assign_staff_id
                     FROM WorkProgress wp
                     INNER JOIN FaultedDevice fd ON wp.work_progress_id = fd.work_progress_id
                     INNER JOIN ResidentReportedIssue rri ON fd.issue_id = rri.issue_id\n""";
        return jdbcTemplate.query(SQL, new WorkProgressAndIssueByResidentReportedIssueRowMapper());
    }

    public List<WorkProgressAndStaffNameRecord> findWorkProgressAndStaffName() {
        String SQL = """
                SELECT wp.*, r.issue_id, s.fullname
                FROM WorkProgress wp
                JOIN FaultedDevice fd ON wp.work_progress_id = fd.work_progress_id
                JOIN ResidentReportedIssue r ON fd.issue_id = r.issue_id
                JOIN Staff s ON fd.assign_staff_id = s.staff_id;""";
        return jdbcTemplate.query(SQL, new WorkProgressAndStaffNameRecordRowMapper());
    }
}
