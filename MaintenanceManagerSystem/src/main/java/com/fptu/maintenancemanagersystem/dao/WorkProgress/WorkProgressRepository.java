package com.fptu.maintenancemanagersystem.dao.WorkProgress;

import com.fptu.maintenancemanagersystem.model.WorkProgress;
import com.fptu.maintenancemanagersystem.model.WorkProgressAndIssueByResidentReportedIssue;
import com.fptu.maintenancemanagersystem.model.WorkProgressAndStaffNameRecord;
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
public class WorkProgressRepository {
    @Autowired
    JdbcTemplate jdbcTemplate;

    @Autowired
    NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public WorkProgress findById(int id) {
        String SQL = "SELECT * FROM WorkProgress WHERE work_progress_id = ?";

        try {
            return jdbcTemplate.queryForObject(SQL, new Object[]{id}, new BeanPropertyRowMapper<>(WorkProgress.class));
        } catch (Exception e) {
            return null;
        }
    }

    public List<WorkProgressAndIssueByResidentReportedIssue> findWorkProgressAndIssueByResidentReportedIssue() {
        String SQL = """
                SELECT wp.*, rri.*, fd.assign_staff_id
                FROM WorkProgress wp
                INNER JOIN FaultedDevice fd ON wp.work_progress_id = fd.work_progress_id
                INNER JOIN ResidentReportedIssue rri ON fd.issue_id = rri.issue_id""";
        return jdbcTemplate.query(SQL, new WorkProgressAndIssueByResidentReportedIssueRowMapper());
    }

    public List<WorkProgressAndStaffNameRecord> findWorkProgressAndStaffName() {
        String SQL = """
                SELECT DISTINCT wp.*, r.issue_id, s.fullname
                FROM WorkProgress wp
                JOIN FaultedDevice fd ON wp.work_progress_id = fd.work_progress_id
                JOIN ResidentReportedIssue r ON fd.issue_id = r.issue_id
                JOIN Staff s ON fd.assign_staff_id = s.staff_id;""";
        return jdbcTemplate.query(SQL, new WorkProgressAndStaffNameRecordRowMapper());
    }

    private void insertWorkProgress(LocalDate deadline) {
        Date deadlineDate = Date.valueOf(deadline);
        Date currentDate = Date.valueOf(LocalDate.now());

        String sql = """
                insert into [WorkProgress](deadline_date,created_date) values (:deadlineDate,:currentDate)
                """;
        MapSqlParameterSource params = new MapSqlParameterSource()
                .addValue("deadlineDate", deadlineDate)
                .addValue("currentDate", currentDate);

        namedParameterJdbcTemplate.update(sql, params);
    }

    private void updateWorkProgressForFaultedDevice(int issueId) {
        int currentWorkProgressId = getCurrentWorkProgressId();

        String sql = """
                update [FaultedDevice] set work_progress_id=:currentWorkProgressId where issue_id = :issueId""";


        MapSqlParameterSource params = new MapSqlParameterSource()
                .addValue("currentWorkProgressId", currentWorkProgressId)
                .addValue("issueId", issueId);

        namedParameterJdbcTemplate.update(sql, params);
    }

    @Transactional
    public void setDeadlineByIssueId(int issueId, LocalDate deadline) {
        insertWorkProgress(deadline);
        updateWorkProgressForFaultedDevice(issueId);
    }

    private Integer getCurrentWorkProgressId() {
        return jdbcTemplate.queryForObject("SELECT IDENT_CURRENT('WorkProgress')", Integer.class);
    }

    public WorkProgressAndStaffNameRecord findWorkProgressAndStaffNameByIssueId(int issueID) {
        String SQL = """
                SELECT DISTINCT wp.*, r.issue_id, s.fullname
                FROM WorkProgress wp
                JOIN FaultedDevice fd ON wp.work_progress_id = fd.work_progress_id
                JOIN ResidentReportedIssue r ON fd.issue_id = r.issue_id
                JOIN Staff s ON fd.assign_staff_id = s.staff_id
                WHERE r.issue_id = ?""";
        try {
            return jdbcTemplate.queryForObject(SQL, new Object[]{issueID}, new WorkProgressAndStaffNameRecordRowMapper());
        } catch (Exception e) {
            return null;
        }
    }

    public List <WorkProgressAndIssueByResidentReportedIssue> getWorkProgressAndStaffNameBySignedInStaff(int signedInStaffId) {
        String sql = """
                 SELECT distinct  wp.*, rri.*, fd.assign_staff_id
                                FROM WorkProgress wp
                                INNER JOIN FaultedDevice fd ON wp.work_progress_id = fd.work_progress_id
                                INNER JOIN ResidentReportedIssue rri ON fd.issue_id = rri.issue_id
                				where assign_staff_id =  ?
                				""";
        try {
            return jdbcTemplate.query(sql, new Object[]{signedInStaffId}, new WorkProgressAndIssueByResidentReportedIssueRowMapper());
        }catch (Exception e){
            return null;
        }
    }
}
