package com.fptu.maintenancemanagersystem.dao.ResidentReportedIssue;

import com.fptu.maintenancemanagersystem.model.ReportedIssueByFaultedDeviceRecord;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ReportedIssueByFaultedDeviceRowMapper implements RowMapper<ReportedIssueByFaultedDeviceRecord> {

    @Override
    public ReportedIssueByFaultedDeviceRecord mapRow(ResultSet rs, int rowNum) throws SQLException {

        return new ReportedIssueByFaultedDeviceRecord(
                rs.getInt("faulted_device_id"),
                rs.getInt("issue_id"),
                rs.getString("resident_name"),
                rs.getString("resident_phone_number"),
                rs.getString("resident_email"),
                rs.getString("description"),
                rs.getDate("date_reported").toLocalDate(),
                rs.getInt("room_id"),
                rs.getString("room_number")

        );
    }
}
