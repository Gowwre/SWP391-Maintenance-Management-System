package com.fptu.maintenancemanagersystem.model;

import java.time.LocalDate;


public record ReportedIssueByFaultedDeviceRecord(int faultedDeviceId, int issueId, String residentName, String residentPhone,
                                                 String residentEmail, String description, LocalDate reportedDate, int roomId,
                                                 String roomNumber, int equipmentId) {
    public ReportedIssueByFaultedDeviceRecord(int faultedDeviceId, int issueId, String residentName, String residentPhone, String residentEmail, String description, LocalDate reportedDate, int roomId, String roomNumber, int equipmentId) {
        this.faultedDeviceId = faultedDeviceId;
        this.issueId = issueId;
        this.residentName = residentName;
        this.residentPhone = residentPhone;
        this.residentEmail = residentEmail;
        this.description = description;
        this.reportedDate = reportedDate;
        this.roomId = roomId;
        this.roomNumber = roomNumber;
        this.equipmentId = equipmentId;

    }
}

