package com.fptu.maintenancemanagersystem.dao.ResidentReportedIssue;

import com.fptu.maintenancemanagersystem.dao.WorkProgress.WorkProgressAndIssueByResidentReportedIssueRowMapper;
import com.fptu.maintenancemanagersystem.model.dto.ResidentIssueReportedAndWorkProgress;
import com.fptu.maintenancemanagersystem.model.dto.ResidentReportedIssueFaultedDeviceWorkProgressStaffRoomDTO;
import com.fptu.maintenancemanagersystem.model.dto.SubmittedReportedIssuesDTO;
import com.fptu.maintenancemanagersystem.model.dto.WorkProgressAndIssueByResidentReportedIssue;
import com.fptu.maintenancemanagersystem.model.entities.ResidentReportedIssue;
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

    @Autowired
    NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public List<ResidentReportedIssue> getAll() {
        String SQL = """
                SELECT * FROM [ResidentReportedIssue]

                """;
        return jdbcTemplate.query(SQL, new BeanPropertyRowMapper<>(ResidentReportedIssue.class));
    }


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
        try {
            return jdbcTemplate.queryForObject("SELECT IDENT_CURRENT('ResidentReportedIssue')", Integer.class);
        } catch (Exception e) {
            return -1;
        }
    }

    public List<WorkProgressAndIssueByResidentReportedIssue> getReportedIssueByFaultedDevice(int issueId) {
        String SQL = "select* from ResidentReportedIssue ri join FaultedDevice fd\n" +
                "on ri.issue_id = fd.issue_id\n" +
                "join Room r on r.room_id = ri.room_id\n" +
                "where ri.issue_id=?";
        return jdbcTemplate.query(SQL, new Object[]{issueId}, new WorkProgressAndIssueByResidentReportedIssueRowMapper());
    }

    public ResidentReportedIssue getResidentReportedIssueById(int issueId) {
        String SQL = "select* from ResidentReportedIssue where issue_id=?";

        try {
            return jdbcTemplate.queryForObject(SQL, new Object[]{issueId}, new ResidentReportedIssueRowMapper());
        } catch (Exception e) {
            return null;
        }
    }

    public List<SubmittedReportedIssuesDTO> getSubmittedReportedIssuesByResidentEmail(String residentEmail) {
        String sql = """
                SELECT DISTINCT  date_reported,room_number,resident_name,r.issue_id,s.fullname,deadline_date,resident_completion_confirmation
                                                          FROM WorkProgress wp
                                                          JOIN FaultedDevice fd ON wp.work_progress_id = fd.work_progress_id
                                                          JOIN ResidentReportedIssue r ON fd.issue_id = r.issue_id
                                                          JOIN Staff s ON fd.assign_staff_id = s.staff_id
                                          join Room room on r.room_id = room.room_id
                                                            Where r.resident_email = ?
                                          order by date_reported desc
                """;
        return jdbcTemplate.query(sql, new Object[]{residentEmail}, (rs, rowNum) -> new SubmittedReportedIssuesDTO(
                rs.getDate("date_reported").toLocalDate(),
                rs.getString("room_number"),
                rs.getString("resident_name"),
                rs.getInt("issue_id"),
                rs.getString("fullname"),
                rs.getDate("deadline_date").toLocalDate(),
                rs.getBoolean("resident_completion_confirmation")));
    }



    public ResidentIssueReportedAndWorkProgress getResidentIssueReportedAndWorkProgressByIssueId(int issueId) {
        String SQL = """
                SELECT DISTINCT wp.*, r.*, s.fullname
                                FROM WorkProgress wp
                                JOIN FaultedDevice fd ON wp.work_progress_id = fd.work_progress_id
                                JOIN ResidentReportedIssue r ON fd.issue_id = r.issue_id
                                JOIN Staff s ON fd.assign_staff_id = s.staff_id
                                Where r.issue_id = ?
                """;
        return jdbcTemplate.queryForObject(SQL, new Object[]{issueId}, new ResidentIssueReportedAndWorkProgressRowMapper());
    }

    public void confirmWorkCompletion(int issueId, String residentPhoneNumber) {
        String sql = "Update [ResidentReportedIssue] set resident_completion_confirmation = 1 where issue_id = :issueId and resident_phone_number = :residentPhoneNumber";

        MapSqlParameterSource parameters = new MapSqlParameterSource()
                .addValue("issueId", issueId)
                .addValue("residentPhoneNumber", residentPhoneNumber);

        namedParameterJdbcTemplate.update(sql, parameters);
    }

    public List<ResidentReportedIssueFaultedDeviceWorkProgressStaffRoomDTO> getAllResidentReportedIssueFaultedDeviceWorkProgressStaffRoom() {
        String sql = """
                select distinct date_reported,room_number,
                                                           resident_name,
                                                           ResidentReportedIssue.issue_id,
                                                           fullname,
                                                           work_status,
                                                           completed_date,
                                                           deadline_date
                                           from ResidentReportedIssue
                                                    full join FaultedDevice FD on ResidentReportedIssue.issue_id = FD.issue_id
                                                    full join Staff on Staff.staff_id = FD.assign_staff_id
                                                    full join WorkProgress WP on FD.work_progress_id = WP.work_progress_id
                                                    full join Room R2 on ResidentReportedIssue.room_id = R2.room_id
                                           where ResidentReportedIssue.issue_id is not null
                                           order by work_status
                """;

        return jdbcTemplate.query(sql, (rs, rowNum) -> new ResidentReportedIssueFaultedDeviceWorkProgressStaffRoomDTO(
                rs.getDate("date_reported").toLocalDate(),
                rs.getString("room_number"),
                rs.getString("resident_name"),
                rs.getInt("issue_id"),
                rs.getString("work_status"),
                rs.getString("fullname"),
                rs.getDate("completed_date") == null ? null : rs.getDate("completed_date").toLocalDate(),
                rs.getDate("deadline_date") == null ? null : rs.getDate("deadline_date").toLocalDate()
        ));
    }

    public List<ResidentReportedIssueFaultedDeviceWorkProgressStaffRoomDTO> getAllResidentReportedIssueByStaffId(int assignedStaffId) {
        String sql = """
                select distinct date_reported,
                                room_number,
                                resident_name,
                                rri.issue_id,
                                work_status,
                                s.fullname,
                                wp.completed_date,
                                wp.deadline_date
                from dbo.ResidentReportedIssue rri
                         inner join
                     dbo.FaultedDevice fd
                     on rri.issue_id = fd.issue_id
                         join WorkProgress wp
                              on fd.work_progress_id = wp.work_progress_id
                         join Staff s
                              on s.staff_id = fd.assign_staff_id
                         join Room r
                              on r.room_id = rri.room_id
                where staff_id = ?
                order by date_reported desc 
                """;

        return jdbcTemplate.query(sql, new Object[]{assignedStaffId}, (rs, rowNum) -> new ResidentReportedIssueFaultedDeviceWorkProgressStaffRoomDTO(
                rs.getDate("date_reported").toLocalDate(),
                rs.getString("room_number"),
                rs.getString("resident_name"),
                rs.getInt("issue_id"),
                rs.getString("work_status"),
                rs.getString("fullname"),
                rs.getDate("completed_date") == null ? null : rs.getDate("completed_date").toLocalDate(),
                rs.getDate("deadline_date") == null ? null : rs.getDate("deadline_date").toLocalDate()
        ));
    }
}
