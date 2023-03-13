package com.fptu.maintenancemanagersystem.dao.WorkProgress;

import com.fptu.maintenancemanagersystem.model.WorkProgress;
import com.fptu.maintenancemanagersystem.model.WorkProgressAndIssueByResidentReportedIssue;
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
        String SQL = "SELECT * FROM WorkProgress WHERE workProgressId = ?";
        return jdbcTemplate.queryForObject(SQL, new Object[]{id}, new BeanPropertyRowMapper<>(WorkProgress.class));
    }

    public List<WorkProgressAndIssueByResidentReportedIssue> findWorkProgressAndIssueByResidentReportedIssue() {
        String SQL = "SELECT wp.*, rri.*, fd.assign_staff_id\n" +
                     "FROM WorkProgress wp\n" +
                     "INNER JOIN FaultedDevice fd ON wp.work_progress_id = fd.work_progress_id\n" +
                     "INNER JOIN ResidentReportedIssue rri ON fd.issue_id = rri.issue_id\n";
        return jdbcTemplate.query(SQL, new WorkProgressAndIssueByResidentReportedIssueRowMapper());
    }
}
