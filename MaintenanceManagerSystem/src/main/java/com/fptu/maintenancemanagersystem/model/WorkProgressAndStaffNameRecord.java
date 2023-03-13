package com.fptu.maintenancemanagersystem.model;

import java.time.LocalDate;

public record WorkProgressAndStaffNameRecord (int workProgressId, LocalDate deadlineDate, LocalDate createdDate,
                                              String workStatus, LocalDate completedDate, int issueId, String fullName) {
    public WorkProgressAndStaffNameRecord(int workProgressId, LocalDate deadlineDate, LocalDate createdDate, String workStatus, LocalDate completedDate, int issueId, String fullName) {
        this.workProgressId = workProgressId;
        this.deadlineDate = deadlineDate;
        this.createdDate = createdDate;
        this.workStatus = workStatus;
        this.completedDate = completedDate;
        this.issueId = issueId;
        this.fullName = fullName;
    }
}
