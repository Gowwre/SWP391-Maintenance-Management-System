package com.fptu.maintenancemanagersystem.model.dto;

import java.time.LocalDate;

public record SubmittedReportedIssuesDTO(LocalDate dateReported, String roomNumber, String residentName, int issueId, String staffName, LocalDate deadlineDate, boolean residentCompletionConfirmation) {
    public SubmittedReportedIssuesDTO(LocalDate dateReported, String roomNumber, String residentName, int issueId, String staffName, LocalDate deadlineDate, boolean residentCompletionConfirmation) {
        this.dateReported = dateReported;
        this.roomNumber = roomNumber;
        this.residentName = residentName;
        this.issueId = issueId;
        this.staffName = staffName;
        this.deadlineDate = deadlineDate;
        this.residentCompletionConfirmation = residentCompletionConfirmation;
    }
}
