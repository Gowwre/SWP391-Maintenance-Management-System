package com.fptu.maintenancemanagersystem.model.dto;

import java.time.LocalDate;

public record ResidentReportedIssueFaultedDeviceWorkProgressStaffRoomDTO(LocalDate dateReported, String roomNumber, String residentFullName, int issueId, String workStatus, String staffFullName, LocalDate completedDate, LocalDate deadlineDate) {
    public ResidentReportedIssueFaultedDeviceWorkProgressStaffRoomDTO(LocalDate dateReported, String roomNumber, String residentFullName, int issueId, String workStatus, String staffFullName, LocalDate completedDate, LocalDate deadlineDate) {
        this.dateReported = dateReported;
        this.roomNumber = roomNumber;
        this.residentFullName = residentFullName;
        this.issueId = issueId;
        this.workStatus = workStatus;
        this.staffFullName = staffFullName;
        this.completedDate = completedDate;
        this.deadlineDate = deadlineDate;
    }
}
