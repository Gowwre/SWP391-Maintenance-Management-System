package com.fptu.maintenancemanagersystem.model.dto;

import java.time.LocalDate;


public record ReportedIssueByFaultedDeviceRecord(int faultedDeviceId, int issueId, String residentName,
                                                 String residentPhone,
                                                 String residentEmail, String description, LocalDate reportedDate,
                                                 int roomId,
                                                 String roomNumber) {
}

