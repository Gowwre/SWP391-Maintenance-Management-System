package com.fptu.maintenancemanagersystem.model;

import java.time.LocalDate;


public record WorkProgressAndIssueByResidentReportedIssue(int workProgressId, LocalDate deadlineDate, LocalDate createdDate, String workStatus, LocalDate completedDate,
                                                          int issueId, int roomId, String residentName, String residentEmail,
                                                          String residentPhoneNumber, String description, LocalDate dateReported, String residentCompletionConfirmation, int assignStaffId) {
    public WorkProgressAndIssueByResidentReportedIssue(int workProgressId, LocalDate deadlineDate, LocalDate createdDate, String workStatus, LocalDate completedDate, int issueId, int roomId, String residentName, String residentEmail, String residentPhoneNumber, String description, LocalDate dateReported, String residentCompletionConfirmation, int assignStaffId) {
        this.workProgressId = workProgressId;
        this.deadlineDate = deadlineDate;
        this.createdDate = createdDate;
        this.workStatus = workStatus;
        this.completedDate = completedDate;
        this.issueId = issueId;
        this.roomId = roomId;
        this.residentName = residentName;
        this.residentEmail = residentEmail;
        this.residentPhoneNumber = residentPhoneNumber;
        this.description = description;
        this.dateReported = dateReported;
        this.residentCompletionConfirmation = residentCompletionConfirmation;
        this.assignStaffId = assignStaffId;
    }
}

