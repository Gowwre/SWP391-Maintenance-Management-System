package com.fptu.maintenancemanagersystem.model;

import java.time.LocalDate;


public record ReportedIssueByFaultedDeviceRecord(int faultedDeviceId, int issueId, String residentName, String residentPhone,
                                                 String residentEmail, String description, LocalDate reportedDate, int roomId,
                                                 String roomNumber) {
    public ReportedIssueByFaultedDeviceRecord(int faultedDeviceId, int issueId, String residentName, String residentPhone, String residentEmail, String description, LocalDate reportedDate, int roomId, String roomNumber) {
        this.faultedDeviceId = faultedDeviceId;
        this.issueId = issueId;
        this.residentName = residentName;
        this.residentPhone = residentPhone;
        this.residentEmail = residentEmail;
        this.description = description;
        this.reportedDate = reportedDate;
        this.roomId = roomId;
        this.roomNumber = roomNumber;

    }
}

